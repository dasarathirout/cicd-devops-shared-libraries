package cicd.devops.pipeline.stage.prebuild


import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters
import cicd.devops.pipeline.stage.PipelineStage

class PullMain extends PipelineStage{
    PullMain(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Pull Latest Code") {
            jenkinsPipeline.println("==== PULL LATEST CODE ===")
        }
    }
}
