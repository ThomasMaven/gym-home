pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
    agent any
    stages {
        withCredentials([sshUserPrivateKey(credentialsId: 'dev', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
            remote.user = userName
            remote.identityFile = identity
            stage("SSH Steps Rocks!") {
                sshScript remote: remote, script: "accounts/deploy/deploy.sh"
            }
        }
    }
}