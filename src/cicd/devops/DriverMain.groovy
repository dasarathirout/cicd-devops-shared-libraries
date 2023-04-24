package cicd.devops

import cicd.devops.pipeline.builds.JobBuilder
import cicd.devops.pipeline.parameter.JobParameters
import cicd.devops.pipeline.stage.StageBuilder


class DriverMain {
    def getBuild = { ->
        return 'BUILD SUCCESS'
    }

    static void main(String[] args) {
        init()
        println new DriverMain().getBuild
    }

    public static void init(){
        println(new JobParameters().getJobParameters())
        println(new JobBuilder().getJobBuilder())
        println(new StageBuilder().getStageBuilder())
    }

    def run(Closure body) {

    }
}
