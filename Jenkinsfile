pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3.9.16'
    }

    environment {
        NODE_HOME = 'C:\\Program Files\\nodejs'
        PATH = "${NODE_HOME};${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out FusionPay from GitHub...'
                checkout scm
            }
        }

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
                bat 'node -v'
                bat 'npm -v'
            }
        }

        stage('Build Eureka Server') {
            steps {
                dir('backend/eureka-server') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build User Service') {
            steps {
                dir('backend/user-service') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Product Service') {
            steps {
                dir('backend/product-service') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Finance Service') {
            steps {
                dir('backend/finance-service') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Admin Service') {
            steps {
                dir('backend/admin-service1') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('backend/api-gateway') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Angular Frontend') {
            steps {
                dir('frontend/finance-management') {

                    bat 'npm install'

                    bat 'npm run build'
                }
            }
        }
    }

    post {

        success {
            echo '======================================='
            echo 'FusionPay BUILD SUCCESSFUL!'
            echo '======================================='
        }

        failure {
            echo '======================================='
            echo 'FusionPay BUILD FAILED!'
            echo 'Check the stage that failed.'
            echo '======================================='
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}