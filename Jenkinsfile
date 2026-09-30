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
                '''
            }
        }

        stage('Verify Deployment') {
    steps {
        sh '''
            for i in {1..10}; do
                if curl --fail http://localhost:8081/AayushDevOpsApp/; then
                    echo "Application deployed successfully."
                    exit 0
                fi

                echo "Application not ready yet. Retrying..."
                sleep 2
            done

            echo "Application deployment verification failed."
            exit 1
        '''
    }
}
    }

    post {
        success {
            echo 'Pipeline completed successfully. Application is deployed and verified.'
        }

        failure {
            echo 'Pipeline execution failed. Check the stage logs.'
        }
    }
}
