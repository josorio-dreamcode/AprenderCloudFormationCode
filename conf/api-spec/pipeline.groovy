def credentialId ='1f6d32fd-0bb1-45c6-b2e5-f316c3cf353e'

pipeline {
agent any



options {
buildDiscarder logRotator(
        daysToKeepStr: '16',
        numToKeepStr: '10'
    )
    }



stages {



        stage('Config Terraform setup') {
steps {withCredentials([
                    [$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: "${credentialId}", secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                    ]
                ]) {
                    echo "Ejecutando cloudformation..."
                    echo "pwd"
                    echo "npm -v"
                    sh "npm run pack && npm rum deploy"
                }
            }
        }
    }
post {
always {
            cleanWs()
        }
    }
}