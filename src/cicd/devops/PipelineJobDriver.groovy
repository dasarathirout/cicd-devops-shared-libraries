package cicd.devops

import cicd.devops.constants.COMMON
import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.BuildParameters
import cicd.devops.pipeline.stage.PipelineStage
import cicd.devops.pipeline.stage.PipelineStageBuilder

class PipelineJobDriver {

    private final JenkinsPipeline jenkinsPipeline

    PipelineJobDriver(JenkinsPipeline jenkinsPipeline) {
        this.jenkinsPipeline = jenkinsPipeline
    }

    def run(Closure body) {
        Map buildResult = ["QUALITY_CHECK_ENABLED" : true,
                           "REPORT_GENERATED"      : true,
                           "JAR_SIGNED"            : false,
                           "JAR_PUBLISHED"         : false,
                           "DOCKER_IMAGE_SIGNED"   : false,
                           "DOCKER_IMAGE_PUBLISHED": false,
                           "K8S_DEPLOYED"          : false]
        BuildParameters buildParameters = initialiseParameters(body)
        try {
            jenkinsPipeline.println("parameters, ${buildParameters.toString()}")
            initialiseBuild(buildParameters)
            jenkinsPipeline.println(COMMON.MESSAGE_BUILD_PASSED)
        } catch (Throwable e) {
            jenkinsPipeline.println(COMMON.MESSAGE_BUILD_FAILED)
            jenkinsPipeline.println("${e}")
        } finally {
            return buildResult
        }
    }

    void initialiseBuild(BuildParameters parameters) {
        List<PipelineStage> buildStages = PipelineStageBuilder.preparePipelineStages(this.jenkinsPipeline, parameters)
        for (PipelineStage stage : buildStages) {
            jenkinsPipeline.println("${COMMON.MESSAGE_LOG_LINE} BEFORE STAGE ${stage.getClass().name} BUILD STATUS = ${jenkinsPipeline.buildStatus()} ${COMMON.MESSAGE_LOG_LINE}")
            stage.run()
            jenkinsPipeline.println("${COMMON.MESSAGE_LOG_LINE} AFTER STAGE ${stage.getClass().name} BUILD STATUS = ${jenkinsPipeline.buildStatus()} ${COMMON.MESSAGE_LOG_LINE}")
        }
    }

    private BuildParameters initialiseParameters(Closure body) {
        try {
            Map config = [:]
            body.resolveStrategy = Closure.DELEGATE_ONLY
            body.delegate = config
            body()
            jenkinsPipeline.println("CONFIG : ${config}")
            return BuildParameters.getBuildParametersFrom(jenkinsPipeline, config)
        } catch (Throwable e) {
            jenkinsPipeline.println("${e}")
            throw e
        }
    }
}
