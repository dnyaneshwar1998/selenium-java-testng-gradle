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
                    env.JAVA_HOME = sh(script: '/usr/libexec/java_home -v 25', returnStdout: true).trim()
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
            archiveArtifacts artifacts: 'build/reports/tests/test/**,build/reports/extent/**,build/allure-results/**', allowEmptyArchive: true

            publishHTML(target: [
                reportName: 'Extent Report',
                reportDir: 'build/reports/extent',
                reportFiles: 'extent-report.html',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'build/allure-results']]
            ])
        }
    }
}
