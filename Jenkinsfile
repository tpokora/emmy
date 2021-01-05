pipeline {
  agent any
  stages {
    stage('Cloning Git repo') {
      steps {
        git(url: 'https://github.com/tpokora/emmy', branch: 'master')
      }
    }

    stage('Run tests') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew clean'
        sh './gradlew test'
      }
    }

  }
}