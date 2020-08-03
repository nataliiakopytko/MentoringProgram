pipeline{
    agent any
    stages{
        stage ('Compile'){
            steps{
                mvn clean compile
            }
        }
        stage('Test'){
            steps{
                mvn test
            }
        }
        stage('Verify'){
            steps{
                mvn verify
            }
        }
    }
}