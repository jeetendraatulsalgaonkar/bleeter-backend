pipeline {
    agent { docker { image 'gradle:7.4.2-jdk8' } }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}