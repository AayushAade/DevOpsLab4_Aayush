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
                    echo "Waiting for Tomcat deployment..."

                    for i in $(seq 1 20)
                    do
                        echo "Verification attempt $i/20"

                        if curl --fail --silent \
                            http://localhost:8081/AayushDevOpsApp/ > /dev/null
                        then
                            echo "Application deployed and verified successfully."
                            exit 0
                        fi

                        sleep 2
                    done

                    echo "Application deployment verification failed after 40 seconds."
                    exit 1
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
