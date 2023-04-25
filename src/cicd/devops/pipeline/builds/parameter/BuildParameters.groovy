package cicd.devops.pipeline.builds.parameter

import cicd.devops.constants.COMMON
import cicd.devops.pipeline.builds.JenkinsPipeline

class BuildParameters implements JenkinsParameters {

    private final String environment
    private final String projectName
    private final boolean doGenerateReport
    private final boolean isDeployable

    BuildParameters(String environment, String projectName, boolean generateReport, boolean isDeployable){
        this.environment = environment
        this.projectName = projectName
        this.doGenerateReport = generateReport
        this.isDeployable = isDeployable
    }

    @Override
    String artifactName() {
        return this.projectName
    }

    @Override
    String artifactGroup() {
        return COMMON.ARTIFACT_GROUP_NAME
    }

    @Override
    String artifactVersion() {
        return "${getArtifactType(this.branchName())+"-"+this.buildNumber()}"
    }

    @Override
    String branchName() {
        return "BRANCH_NAME"
    }

    @Override
    String jobName() {
        return "JOB_NAME"
    }

    @Override
    Boolean doGenerateReport() {
        return this.doGenerateReport
    }

    @Override
    Boolean isDeployable() {
        return this.isDeployable
    }

    String buildNumber() {
        return "JOB_NAME"
    }

    static String getArtifactType(String branchName){
        switch (branchName.toUpperCase()){
            case "MAIN":
                return 'RELEASE'
            case "MASTER":
                return 'RELEASE'
            default:
                return 'SNAPSHOT'
        }
    }

    static BuildParameters getBuildParametersFrom(JenkinsPipeline jenkinsPipeline, Map configParams){
        return new BuildParameters(
                configParams.ENV as String,
                configParams.PROJECT_NAME as String,
                configParams.REPORT as Boolean,
                configParams.DEPLOY as Boolean
        )
    }

    @Override
    String toString() {
        return """
        BuildParameters{
            environment='$environment', 
            projectName='$projectName', 
            doGenerateReport=$doGenerateReport, 
            isDeployable=$isDeployable
        }"""
    }
}
