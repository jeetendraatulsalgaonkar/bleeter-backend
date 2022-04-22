pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }

    triggers {
        githubPush()
    }
}