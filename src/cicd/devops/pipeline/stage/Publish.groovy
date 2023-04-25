package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

class Publish extends PipelineStage{
    Publish(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Publish") {
            jenkinsPipeline.println("★★★ Publish Gradle Build ★★★")
        }
    }
}
