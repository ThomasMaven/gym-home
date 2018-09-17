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
                script {
                    commitId = sh(returnStdout: true, script: 'git rev-parse --short HEAD')
                }
                sh """
                    cd accounts
                    mvn dockerfile:build -Ddockerfile.tag=${commitId}
                    cd ../authorization
                    mvn dockerfile:build -Ddockerfile.tag=${commitId}
                    cd ../config
                    mvn dockerfile:build -Ddockerfile.tag=${commitId}
                    cd ../exercises
                    mvn dockerfile:build -Ddockerfile.tag=${commitId}
                    cd ../web
                    mvn dockerfile:build -Ddockerfile.tag=${commitId}
                """
            }
        }
        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'username', passwordVariable: 'password')]) {
                    sh """
                        cd accounts
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password}
                        cd ../authorization
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password}
                        cd ../config
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password}
                        cd ../exercises
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password}
                        cd ../web
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password}
                    """
                }
            }
        }
        stage("Deploy") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dev-ssh', usernameVariable: 'username', passwordVariable: 'password')]) {
                    script {
                        remote = [:]
                        remote.name = 'dev'
                        remote.host = '127.0.0.1'
                        remote.allowAnyHosts = true
                        remote.user = username
                        remote.password = password
                    }
                    sshPut remote: remote, from: 'config/templates', into: 'config'
                    sshCommand remote: remote, command: 'kubectl apply -f config/templates'

                    sshPut remote: remote, from: 'accounts/templates', into: 'accounts'
                    sshCommand remote: remote, command: 'kubectl apply -f accounts/templates'

                    sshPut remote: remote, from: 'authorization/templates', into: 'authorization'
                    sshCommand remote: remote, command: 'kubectl apply -f authorization/templates'

                    sshPut remote: remote, from: 'exercises/templates', into: 'exercises'
                    sshCommand remote: remote, command: 'kubectl apply -f exercises/templates'

                    sshPut remote: remote, from: 'web/templates', into: 'web'
                    sshCommand remote: remote, command: 'kubectl apply -f web/templates'
                }
            }
        }
    }
}