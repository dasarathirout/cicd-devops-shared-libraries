import cicd.devops.PipelineJobDriver

import cicd.devops.pipeline.builds.JenkinsPipeline

def call(Closure body) {
    timestamps{
        new PipelineJobDriver(new JenkinsPipeline(this)).run(body)
    }
}
