pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
    agent any
    stages {
        def remote = [:]
        remote.name = 'dev'
        remote.host = '127.0.0.1'
        remote.user = 'jakub'
        stage("SSH Steps Rocks!") {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'dev', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                    remote.user = userName
                    remote.identityFile = identity
                    sshScript remote: remote, script: "accounts/deploy/deploy.sh"
                }
            }
        }
    }
}