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
                    mvn dockerfile:build -dockerfile.tag=${env.BUILD_NUMBER}
                    cd ../authorization
                    mvn dockerfile:build -dockerfile.tag=${env.BUILD_NUMBER}
                    cd ../config
                    mvn dockerfile:build -dockerfile.tag=${env.BUILD_NUMBER}
                    cd ../exercises
                    mvn dockerfile:build -dockerfile.tag=${env.BUILD_NUMBER}
                    cd ../web
                    mvn dockerfile:build -dockerfile.tag=${env.BUILD_NUMBER}
                """
            }
        }
        stage('Push Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'username', passwordVariable: 'password')]) {
                    sh """
                        cd accounts
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password} -dockerfile.tag=${env.BUILD_NUMBER}
                        cd ../authorization
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password} -dockerfile.tag=${env.BUILD_NUMBER}
                        cd ../config
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password} -dockerfile.tag=${env.BUILD_NUMBER}
                        cd ../exercises
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password} -dockerfile.tag=${env.BUILD_NUMBER}
                        cd ../web
                        mvn dockerfile:push -Ddockerfile.username=${username} -Ddodckerfile.password=${password} -dockerfile.tag=${env.BUILD_NUMBER}
                    """
                }
            }
        }
        stage("Deploy") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dev-ssh', usernameVariable: 'username', passwordVariable: 'password')]) {
                    script {
                        def remote = [:]
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