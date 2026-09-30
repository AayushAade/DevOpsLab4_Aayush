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

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.war',
                                 fingerprint: true
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh '''
                    rm -rf /opt/tomcat/webapps/AayushDevOpsApp
                    rm -f /opt/tomcat/webapps/AayushDevOpsApp.war

                    cp target/AayushDevOpsApp.war /opt/tomcat/webapps/

                    echo "WAR copied to Tomcat."
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                    echo "Waiting for Tomcat to deploy the WAR..."
                    sleep 5

                    echo "Checking application URL..."

                    curl --fail --silent --show-error \
                        http://localhost:8081/AayushDevOpsApp/ \
                        > /dev/null

                    echo "Application deployed and verified successfully."
                '''
            }
        }
    }

    post {

        success {
            echo 'Build, Test, Package, Archive, Deployment and Verification completed successfully.'
        }

        failure {
            echo 'Pipeline execution failed. Check the stage logs.'
        }
    }
}
