pipeline {
   agent any

    environment {
        PORT="80"
        IMAGE_TAG="employee-service-image"
        CONTAINER_NAME="employee-service-container"
        DB_URL=credentials('[db-url]')
        DB_USER=credentials('[db-username]')
        DB_PASS=credentials('[db-password]')
    }

   stages {
      stage('checkout'){
          steps {
               git branch: 'master', url: 'https://gitlab.com/210301-java-azure/project3/revature-max-employee-service.git'
           }
      }
      stage('clean') {
         steps {
            sh 'sh gradlew clean'
         }
      }
      stage('package') {
         steps {
            sh 'sh gradlew bootJar'
         }
      }
      stage('remove previous image if exists') {
            steps {
                sh 'docker rmi -f ${IMAGE_TAG} || true'
            }
        }

       stage('create image') {
            steps {
                sh 'docker build -t ${IMAGE_TAG} -f Dockerfile .'
            }
        }
        stage('remove previous container if exists') {
            steps {
                sh 'docker stop ${CONTAINER_NAME} || true'
            }
        }
        stage('create container') {
            steps {
                sh 'docker run -d --rm -p ${PORT}:${PORT} -e DB_URL=${DB_URL} -e DB_USER=${DB_USER} -e DB_PASS=${DB_PASS} --name ${CONTAINER_NAME} ${IMAGE_TAG}'
            }
        }
    }
}
