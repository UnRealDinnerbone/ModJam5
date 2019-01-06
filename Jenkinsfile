node {
   stage 'Checkout'

   checkout scm

   stage 'Build'

   sh "rm -rf build/libs/"
   sh "chmod +x gradlew"
   sh "./gradlew build --refresh-dependencies --stacktrace"

   stage "Archive artifacts"

   archive 'build/libs/*'
   
   
}

def createRelease(name) {
  stage ('createRelease') {
        def payload = JsonOutput.toJson(["tag_name": "v-${name}", "name": "GitHub Action triggered release: ${name}", "body": "This release has been created with the help of a Jenkins single-shot master running inside of a GitHub Action. For more details visit https://github.com/jonico/jenkinsfile-runner-github-actions"])
        def apiUrl = "https://api.github.com/repos/${env.GITHUB_REPOSITORY}/releases"
        mysh("curl -s --output /build/libs/* -H \"Authorization: Token ${env.GITHUB_TOKEN}\" -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d '${payload}' ${apiUrl}")
    }
}

def mysh(cmd) {
    sh('#!/bin/sh -e\n' + cmd)
}