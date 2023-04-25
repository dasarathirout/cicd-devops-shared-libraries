package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

abstract class PipelineStage {

    final JenkinsPipeline jenkinsPipeline
    final JenkinsParameters jenkinsParameters

    PipelineStage(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        this.jenkinsPipeline = jenkinsPipeline
        this.jenkinsParameters = jenkinsParameters
    }

    abstract Object run()

    Object stage(String stageName, Closure action) {
        return jenkinsPipeline.stage(stageName, action)
    }
}