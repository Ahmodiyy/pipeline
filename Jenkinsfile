pipeline {
    agent {
        label "pipeline"
    }
    stages {
        stage("Checkout"){
            steps{
                git url : "https://github.com/Ahmodiyy/pipeline.git", branch: "main"
            }
        }
        stage("Build") {
            steps {
                bat "java -version"
                bat "./gradlew --version"
                bat "docker version"
                bat "wsl -d Ubuntu ansible --version"
                bat "./gradlew clean"
                bat "./gradlew build"
            }
        }
          stage("Code coverage") {
               steps {
                    bat "./gradlew jacocoTestReport"
                    publishHTML (target: [
                                   reportDir: 'build/reports/jacoco/test/html',
                                   reportFiles: 'index.html',
                                   reportName: "JaCoCo Report"
                              ])
                    bat "./gradlew jacocoTestCoverageVerification"
               }
           }
           stage("Static code analysis") {
                steps {
                     bat "./gradlew checkstyleMain"
                     publishHTML (target: [
                          reportDir: 'build/reports/checkstyle/',
                          reportFiles: 'main.html',
                          reportName: "Checkstyle Report"
                      ])
                }
            }
            stage("Docker Build") {
                 steps {
                       bat "docker build -t ahmodiyy/pipeline ."
                 }
            }
            stage("Docker Push") {
                 steps {
                        bat "docker push ahmodiyy/pipeline"
                 }
            }
             stage("Deploy to staging") {
                 steps {
                      bat "docker run -d --rm -p 7070:7070 --name pipeline ahmodiyy/pipeline"
                 }
             }
            stage("Acceptance Test") {
                 steps {
                        sleep 60
                        bat "./gradlew acceptanceTest -Durl=http://localhost:7070"
                 }
            }

    }

     post {
            always {
                mail to: 'codeble101@gmail.com',
                     subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
                     body: "Your build completed check : ${env.BUILD_URL}"

                 slackSend channel: '#test', color: 'danger', message: "The pipeline ${currentBuild.fullDisplayName} failed."
                 sh "docker stop pipeline"
            }
        }

}
