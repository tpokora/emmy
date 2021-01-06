pipeline {
  agent any
  stages {
    stage('Cloning Git repo') {
      steps {
        git(url: 'https://github.com/tpokora/emmy', branch: 'issue_81')
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
        junit '**/build/test-results/test/*.xml'
      }
    }

    stage('emmy-domain:test') {
      steps {
        sh './gradlew :emmy-domain:test'
        junit '**/build/test-results/test/*.xml'
      }
    }

    stage('emmy-persistance:test') {
      steps {
        sh './gradlew :emmy-persistance:test'
        junit '**/build/test-results/test/*.xml'
      }
    }

    stage('emmy-config:test') {
      steps {
        sh './gradlew :emmy-config:test'
        junit '**/build/test-results/test/*.xml'
      }
    }

    stage('emmy-application:test') {
      steps {
        sh './gradlew :emmy-application:test'
        junit '**/build/test-results/test/*.xml'
      }
    }

  }
}