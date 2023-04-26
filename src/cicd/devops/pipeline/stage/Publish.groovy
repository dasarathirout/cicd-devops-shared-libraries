package cicd.devops.pipeline.stage

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters
import cicd.devops.pipeline.builds.report.PublishHTML

class Publish extends PipelineStage{
    Publish(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        super(jenkinsPipeline, jenkinsParameters)
    }

    @Override
    Object run() {
        return stage("Publish") {
            jenkinsPipeline.println("==== Publish Gradle Build ====")

            jenkinsPipeline.publishHtmlReport(new PublishHTML(
                    jenkinsPipeline.getWorkspaceDirectory()+"\\service-database\\build\\reports\\tests\\test",
                    "index.html",
                    "service-database"
            ))

            jenkinsPipeline.publishHtmlReport(new PublishHTML(
                    jenkinsPipeline.getWorkspaceDirectory()+"\\service-zero\\build\\reports\\tests\\test",
                    "index.html",
                    "service-zero"
            ))

            jenkinsPipeline.publishHtmlReport(new PublishHTML(
                    jenkinsPipeline.getWorkspaceDirectory()+"\\service-one\\build\\reports\\tests\\test",
                    "index.html",
                    "service-one"
            ))

            jenkinsPipeline.publishHtmlReport(new PublishHTML(
                    jenkinsPipeline.getWorkspaceDirectory()+"\\service-one\\build\\reports\\test\\test",
                    "index.html",
                    "service-two"
            ))

        }
    }
}
