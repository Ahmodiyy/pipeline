pipeline {
    agent any
    stages {
        stage("Checkout"){
            steps{
                git url : "https://github.com/Ahmodiyy/pipeline.git", branch: "main"
            }
        }
        stage("Compile") {
            steps {
                sh "java -version"
                sh "docker version"
                sh "kubectl version"
                sh "chmod +x gradlew"
                sh "./gradlew --version"
                sh "./gradlew compileJava"
            }
        }
         stage("Unit test") {
               steps {
                    sh "./gradlew test"
               }
          }

          stage("Code coverage") {
               steps {
                    sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                                   reportDir: 'build/reports/jacoco/test/html',
                                   reportFiles: 'index.html',
                                   reportName: "JaCoCo Report"
                              ])
                    sh "./gradlew jacocoTestCoverageVerification"
               }
           }
           stage("Static code analysis") {
                steps {
                     sh "./gradlew checkstyleMain"
                     publishHTML (target: [
                          reportDir: 'build/reports/checkstyle/',
                          reportFiles: 'main.html',
                          reportName: "Checkstyle Report"
                      ])
                }
            }
            stage("Generate bootJar") {
                 steps {
                      sh "./gradlew bootJar"
                 }
            }
            stage("Docker Build") {
                 steps {
                       sh "docker build -t ahmodiyy/pipeline ."
                 }
            }
            stage("Docker login") {
                  steps {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub',
                                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                      sh "docker login --username $USERNAME --password $PASSWORD"
                    }
                  }
                }
            stage("Docker Push") {
                 steps {
                        sh "docker push ahmodiyy/pipeline"
                 }
            }
             stage("Deploy to staging") {
                 steps {
                      sh "docker run -d --rm -p 7070:7070 --name pipeline ahmodiyy/pipeline"
                 }
             }
            stage("Acceptance Test") {
                 steps {
                        sleep 60
                        sh "./gradlew acceptanceTest -Durl=http://localhost:7070"
                 }
            }

    }

     post {
            always {
                mail to: 'codeble101@gmail.com',
                     subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
                     body: "Your build completed check : ${env.BUILD_URL}"

                 slackSend channel: '#ahm', color: 'danger', message: "The pipeline ${currentBuild.fullDisplayName} failed."
                 sh "docker stop pipeline"
            }
        }

}
