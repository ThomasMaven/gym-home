pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
    agent any
    stages {
        stage("SSH Steps Rocks!") {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'dev', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                    sshScript remote: remote, script: "accounts/deploy/deploy.sh"
                }
            }
        }
    }
}