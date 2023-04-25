package cicd.devops.pipeline.stage.prebuild


import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters
import cicd.devops.pipeline.stage.PipelineStage

class Parameters extends PipelineStage{
    Parameters(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Build Parameters") {
            jenkinsPipeline.println("★★★ RAW CONFIGS : ${config.toString()}")
        }
    }
}
