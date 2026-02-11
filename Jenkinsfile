pipeline{
	agent any
	
	tools{
		maven 'maven3'
		jdk 'jdk11'
	}
	
	parameters{
		choice(
			name: 'TEST_TYPE',
			choices: ['TDD', 'BDD'],
			description: 'Select test execution type'
		)
	}
	
	stage('Execute Tests'){
		steps{
			script{
				if(params.TEST_TYPE == 'TDD'){
					sh 'mvn test -Dsurefire.suiteXmlFiles=testrunners/testng_tdd_parallel.xml'
				} else{
					sh 'mvn test -Dsurefire.suiteXmlFiles=testrunners/testng_bdd_parallel.xml'
				}
			}
		}
	}
}