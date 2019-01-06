workflow "Upload CurseForge" {
  on = "push"
  resolves = [
    "Build/Upload Jar to CurseForge",
    "GitHub Action for Azure",
  ]
}

action "Build/Upload Jar to CurseForge" {
  uses = "actions/aws/cli@8d31870"
  runs = "./gradlew.sh curseforge"
  env = {
    BUILD_NUMBER = "1"
  }
  secrets = ["CURSE_API_KEY"]
}

action "GitHub Action for Azure" {
  uses = "actions/azure@4919f14"
  secrets = ["CURSE_API_KEY"]
  runs = "./gradlew curseforge"
}
