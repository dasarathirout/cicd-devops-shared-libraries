package cicd.devops.pipeline.builds.parameter
interface JenkinsParameters {
    String artifactName()
    String artifactGroup()
    String artifactVersion()
    String branchName()
    String jobName()
    Boolean doGenerateReport()
    Boolean isDeployable()
}
