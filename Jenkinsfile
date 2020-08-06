pipeline{
    agent any
    stages{
        stage ('Compile'){
            steps{
                withMaven(maven:'mvn_3_6_3'){
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Verify'){
            steps{
                withMaven(maven:'mvn_3_6_3'){
                    sh 'mvn verify'
                }
            }
        }
    }
}