workflow "Upload CurseForge" {
  on = "push"
  resolves = ["Build/Upload Jar to CurseForge"]
}

action "Build/Upload Jar to CurseForge" {
  uses = "actions/aws/cli@8d31870"
  runs = "gradlew curseforge"
  env = {
    BUILD_NUMBER = "1"
  }
  secrets = ["CURSE_API_KEY"]
}
