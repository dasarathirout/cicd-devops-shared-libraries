package cicd.devops.pipeline.stage

class StageBuilder {

    private StageBuilder stageBuilder;

    public StageBuilder(){
        println("Stage Builder")
        stageBuilder= new StageBuilder();
    }

    public StageBuilder getStageBuilder(){
        return this.stageBuilder;
    }
}
