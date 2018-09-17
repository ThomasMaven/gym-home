def remote = [:]
remote.name = 'dev'
remote.host = '127.0.0.1'
remote.user = 'jakub'
remote.allowAnyHosts = true

pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
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
        stage('Push Images') {
            steps {
                sh """
                    cd accounts
                    mvn dockerfile:push
                    cd ../authorization
                    mvn dockerfile:push
                    cd ../config
                    mvn dockerfile:push
                    cd ../exercises
                    mvn dockerfile:push
                    cd ../web
                    mvn dockerfile:push
                """
            }
        }
        stage("Deploy") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dev-pass', usernameVariable: 'username', passwordVariable: 'password')]) {
                    script {
                        remote.user = username
                        remote.password = password
                    }
                    sshPut remote: remote, from: 'config/templates', into: 'templates/config'
                    sshCommand remote: remote, command: 'kubectl apply -f templates/config'

                    sshPut remote: remote, from: 'accounts/templates', into: 'templates/accounts'
                    sshCommand remote: remote, command: 'kubectl apply -f templates/accounts'

                    sshPut remote: remote, from: 'authorization/templates', into: 'templates/authorization'
                    sshCommand remote: remote, command: 'kubectl apply -f templates/authorization'

                    sshPut remote: remote, from: 'exercises/templates', into: 'templates/exercises'
                    sshCommand remote: remote, command: 'kubectl apply -f templates/exercises'

                    sshPut remote: remote, from: 'web/templates', into: 'templates/web'
                    sshCommand remote: remote, command: 'kubectl apply -f templates/web'
                }
            }
        }
    }
}