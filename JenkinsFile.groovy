node {
    stage('Clone Sources') {
        println("Clone Sources")
        git url: 'https://github.com/dasarathirout/cicd-devops-shared-libraries.git'
    }

    stage('Artifactory Configuration') {
    println("Artifactory Configuration")
    }

    stage('Gradle Build') {
        println("Gradle Build")
    }

    stage('Publish Build') {
        println("Publish Build")
    }
}
