package cicd.devops

import cicd.devops.pipeline.builds.JobBuilder
import cicd.devops.pipeline.parameter.JobParameters
import cicd.devops.pipeline.stage.StageBuilder


class DriverMain {
    def getBuild = { ->
        return 'BUILD SUCCESS'
    }

    static void main(String[] args) {
        println new DriverMain().getBuild
    }

    void init(){
        new JobParameters().run()
        new JobBuilder().run()
        new StageBuilder().run()
    }

    def run = { Closure body ->
        init()
    }
}
