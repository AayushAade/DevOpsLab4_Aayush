pipeline {

    agent any

    options {
        skipDefaultCheckout(true)
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    rm -rf /opt/tomcat/webapps/AayushDevOpsApp
                    rm -f /opt/tomcat/webapps/AayushDevOpsApp.war
                    cp target/AayushDevOpsApp.war /opt/tomcat/webapps/
                '''
            }
        }
    }

    post {
        success {
            echo 'Build, Test, Package and Deployment completed successfully.'
        }

        failure {
            echo 'Pipeline execution failed.'
        }
    }
}
