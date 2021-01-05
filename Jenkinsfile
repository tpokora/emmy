pipeline {
  agent any
  stages {
    stage('Cloning Git repo') {
      steps {
        git(url: 'https://github.com/tpokora/emmy', branch: 'master')
      }
    }

    stage('Prepare') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew clean'
      }
    }

    stage('emmy-common:test') {
      steps {
        sh './gradlew :emmy-common:test'
      }
    }
    
    stage('emmy-domain:test') {
      steps {
        sh './gradlew :emmy-domain:test'
      }
    }
    
    stage('emmy-persistance:test') {
      steps {
        sh './gradlew :emmy-persistance:test'
      }
    }
    
    stage('emmy-config:test') {
      steps {
        sh './gradlew :emmy-config:test'
      }
    }
    
    stage('emmy-application:test') {
      steps {
        sh './gradlew :emmy-application:test'
      }
    }

  }
}
