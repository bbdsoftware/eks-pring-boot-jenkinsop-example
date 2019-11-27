podTemplate(
containers: [
    containerTemplate(name: 'jnlp', image: 'jenkins/jnlp-slave:alpine'),
    containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ]) {
  node(POD_LABEL) {
    stage('Build a Maven project') {
      git 'https://github.com/kanzifucius/spring-boot-k8s-jenkinsop-example.git'
      container('maven') {
          sh 'mvn -B clean package'
      }
    }
    stage('Build Integration Tests') {
          container('maven') {
              sh 'mvn -B  verify'
          }
    }
    stage('Build Docker Jib ') {
              container('maven') {
              withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                usernameVariable: 'USER',
                                                passwordVariable: 'PASS')]) {
                  sh 'mvn -B com.google.cloud.tools:jib-maven-plugin:1.6.1:build -Djib.to.auth.username=$USER -Djib.to.auth.password=$PASS'
              }

              }
    }

     stage('Helm Chart Deploy') {
                  container('maven') {
                  withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                    usernameVariable: 'USER',
                                                    passwordVariable: 'PASS')]) {
                      sh 'mvn -B com.deviceinsight.helm:helm-maven-plugin:2.1.0:deploy'
                  }

                  }
        }

  }
}