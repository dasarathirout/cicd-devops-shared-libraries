package cicd.devops.pipeline.stage

class StageBuilder {

    private StageBuilder stageBuilder;

    StageBuilder(){
        println("Stage Builder")
    }

    StageBuilder getStageBuilder(){
        return this.stageBuilder;
    }
}
