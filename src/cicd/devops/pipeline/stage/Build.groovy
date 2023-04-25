package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

class Build extends PipelineStage{

    Build(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Build") {
            jenkinsPipeline.println("==== Gradle Build ====")
            jenkinsPipeline.shell("whoami")
            jenkinsPipeline.shell("dir")
            jenkinsPipeline.shell("gradle clean build test")
        }
    }
}
