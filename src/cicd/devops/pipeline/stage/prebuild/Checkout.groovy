package cicd.devops.pipeline.stage.prebuild

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters
import cicd.devops.pipeline.stage.PipelineStage

class Checkout extends PipelineStage{

    Checkout(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline,jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Code Checkout") {
            jenkinsPipeline.println("===== CODE CHECKOUT =====")
        }
    }
}
