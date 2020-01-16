# jenkins.

## run jenkins
* get .war from https://jenkins.io/
* run: `java -jar jenkins.war`
* jenkins is reachable under: localhost:8080
* initial password can be found here: `~/.jenkins/secrets/initialAdminPassword`
* jenkins default location: `~/.jenkins`
* default user:password: `admin:admin`

## example pipeline
* this pipeline uses credentials and write it to a file: credentials.txt
* create a credentials under: http://localhost:8080/credentials/store/system/domain/_/newCredentials
	* in the example I used: `jenkins_credential_0`
* create a new job: http://localhost:8080/view/all/newJob 
* paste pipeline code
	* based on: https://jenkins.io/doc/pipeline/examples/
```
// This shows a simple example of how to archive the build output artifacts.
node {
    stage "Create build output"
    
    // Make the output directory.
    sh "mkdir -p output"

    // Write an useful file, which is needed to be archived.
    writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."

    // Write an useless file, which is not needed to be archived.
    writeFile file: "output/uselessfile.md", text: "This file is useless, no need to archive it."

    writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."
    
    stage "credentials"
    withCredentials([usernamePassword(credentialsId: 'jenkins_credential_0', passwordVariable: 'bar', usernameVariable: 'foo')]) {
        writeFile file: "output/credentials.txt", text: bar
    }

    stage "Archive build output"
    
    // Archive the build output artifacts.
    archiveArtifacts artifacts: 'output/*.txt', excludes: 'output/*.md'
    
    stage "son"
    
    echo "done."
}
```

## jenkins with github

### jenkins
* configure github under jenkins system configuration
* create a github token
* at jenkins job select the github repository

### github
* under https://github.com/settings/developers > personal token you will see the token created by jenkins.
