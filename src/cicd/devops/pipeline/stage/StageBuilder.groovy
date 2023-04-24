package cicd.devops.pipeline.stage

class StageBuilder {

    private StageBuilder stageBuilder;

    StageBuilder(){
        println("Stage Builder")
    }

    def run = { ->
        println("Job Builder Run")
    }
}
