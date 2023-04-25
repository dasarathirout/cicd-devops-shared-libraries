package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

class Deploy extends PipelineStage{
    Deploy(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Deploy") {
            jenkinsPipeline.println("★★★ Deploy Build ★★★")
        }
    }
}
