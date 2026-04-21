pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Setup Java') {
            steps {
                script {
                    env.JAVA_HOME = tool name: 'jdk25', type: 'jdk'
                    env.PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
                }
                sh 'echo "JAVA_HOME=$JAVA_HOME"'
                sh 'java -version'
                sh './gradlew -version'
            }
        }

        stage('Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean test --no-daemon'
            }
        }
    }

    post {
        always {
            junit testResults: 'build/test-results/test/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'build/reports/tests/test/**', allowEmptyArchive: true
        }
    }
}
