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

  }
}