package cicd.devops.pipeline.stage


import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters
import cicd.devops.pipeline.stage.prebuild.Checkout
import cicd.devops.pipeline.stage.prebuild.Parameters
import cicd.devops.pipeline.stage.prebuild.PullMain

class PipelineStageBuilder {

    private final List<PipelineStage> buildStages = new ArrayList<>()

    private JenkinsPipeline jenkinsPipeline
    private JenkinsParameters jenkinsParameters

    private PipelineStageBuilder(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        this.jenkinsPipeline = jenkinsPipeline
        this.jenkinsParameters = jenkinsParameters
    }

    static List<PipelineStage> preparePipelineStages(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters) {
        return new PipelineStageBuilder(jenkinsPipeline, jenkinsParameters)
                .addPreBuildStages()
                .addBuildStage()
                .addPublishStage()
                .addReportStage()
                .addDeployStage()
                .buildStages
    }

    private PipelineStageBuilder addPreBuildStages() {
        buildStages.add(new Parameters(this.jenkinsPipeline, this.jenkinsParameters))
        buildStages.add(new Checkout(this.jenkinsPipeline, this.jenkinsParameters))
        buildStages.add(new PullMain(this.jenkinsPipeline, this.jenkinsParameters))
        return this
    }

    private PipelineStageBuilder addBuildStage() {
        buildStages.add(new Build(this.jenkinsPipeline, this.jenkinsParameters))
        return this
    }

    private PipelineStageBuilder addPublishStage() {
        buildStages.add(new Publish(this.jenkinsPipeline, this.jenkinsParameters))
        return this
    }

    private PipelineStageBuilder addReportStage() {
        if (jenkinsParameters.doGenerateReport()) {
            buildStages.add(new Report(this.jenkinsPipeline, this.jenkinsParameters))
        }
        return this
    }

    private PipelineStageBuilder addDeployStage() {
        if (jenkinsParameters.isDeployable()) {
            buildStages.add(new Deploy(this.jenkinsPipeline, this.jenkinsParameters))
        }
        return this
    }
}
