package cicd.devops.pipeline.builds

import cicd.devops.constants.COMMON
import cicd.devops.pipeline.metadata.BuildStatus

class JenkinsPipeline {
    private final Object pipelineContext

    JenkinsPipeline(Object pipelineContext) {
        this.pipelineContext = pipelineContext
    }

    Object getJenkinsEnvironmentContext() {
        return pipelineContext
    }

    void setBuildStatus(BuildStatus buildStatus) {
        pipelineContext.currentBuild.result = buildStatus.name()
    }

    String buildStatus() {
        return pipelineContext.currentBuild.currentResult
    }

    void println(String data) {
        pipelineContext.println("[${COMMON.LIB_NAME}] " + data)
    }

    Object stage(String name, Closure action) {
        return pipelineContext.stage(name) {
            try {
                Object actionResult = action()
                setBuildStatus(BuildStatus.SUCCESS)
                return actionResult
            } catch (Throwable e) {
                println("Stage ${name} Got Exception: ${e}.")
                setBuildStatus(BuildStatus.FAILURE)
                throw e
            } finally {
                println(COMMON.MESSAGE_STAGE_FINISHED + " : ${name}")
            }
        }
    }
}
