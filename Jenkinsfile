pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
    agent any
    stages {
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