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
                sh 'chmod +x gradlew'
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


    }

     post {
            always {
                mail to: 'codeble101@gmail.com',
                     subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
                     body: "Your build completed check : ${env.BUILD_URL}"
            }
        }
}
