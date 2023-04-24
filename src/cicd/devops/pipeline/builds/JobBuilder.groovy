package cicd.devops.pipeline.builds

class JobBuilder {

    private JobBuilder jobBuilder;
    public JobBuilder(){
        println("Job Builder")
    }

    public JobBuilder getJobBuilder(){
        return this.jobBuilder;
    }
}
