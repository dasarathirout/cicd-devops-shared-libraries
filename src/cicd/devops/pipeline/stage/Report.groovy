package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

class Report extends PipelineStage{
    Report(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Report") {
            jenkinsPipeline.println("==== Report Build  & Publish ====")
        }
    }
}
