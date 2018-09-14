pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Component tests') {
            steps {
                sh 'mvn clean package -Pcomponent-test'
            }
        }
        stage('Build Images') {
            steps {
                sh """
                    cd accounts
                    mvn dockerfile:build
                    cd ../authorization
                    mvn dockerfile:build
                    cd ../config
                    mvn dockerfile:build
                    cd ../exercises
                    mvn dockerfile:build
                    cd ../web
                    mvn dockerfile:build
                """
            }
        }
    }
}