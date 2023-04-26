package cicd.devops.pipeline.builds

import cicd.devops.constants.COMMON
import cicd.devops.pipeline.builds.report.PublishHTML
import cicd.devops.pipeline.metadata.BuildStatus

import java.nio.file.Path
import java.nio.file.Paths

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

    void shell(String script) {
        if(pipelineContext.isUnix()){
            pipelineContext.sh(script)
        }else{
            pipelineContext.bat(script)
        }
    }

    void writeFile(String fileName, String fileTextContents) {
        shell("echo '${fileTextContents}' > ${fileName}")
    }

    String shellOutput(String script) {
        return pipelineContext.sh(script: script, returnStdout: true)
                .toString().trim()
    }

    Object withEnv(Object env, Closure action) {
        return pipelineContext.withEnv(env, action)
    }

    String readFile(String fileName) {
        return pipelineContext.readFile(fileName)
    }

    void cleanWorkspace() {
        return pipelineContext.cleanWs()
    }

    void checkoutCode() {
        pipelineContext.retry(3) {
            pipelineContext.checkout pipelineContext.scm
        }
    }

    void checkPathExists(String checkPath) {
        println("Asserting that path exists: ${checkPath}")
        if (!pipelineContext.fileExists(checkPath)) {
            throw new RuntimeException("Assertion Failed, ${checkPath} was expected to exist, but it doesn't.")
        }
        shell("dir ${checkPath}")
    }

    String getWorkspaceDirectory() {
        return pipelineContext.env.WORKSPACE
    }

    void publishHtmlReport(PublishHTML report) {
        shell("dir ${report.getReportDir()}")
        checkPathExists(report.reportDir+ File.separator +report.reportFiles)
        Object target = report.targetPublishObject()
        pipelineContext.publishHTML target: target
    }
}
