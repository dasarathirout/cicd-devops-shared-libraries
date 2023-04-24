package cicd.devops.pipeline.builds

class JobBuilder {

    private JobBuilder jobBuilder;
    public JobBuilder(){
        println("Job Builder")
        this.jobBuilder = new JobBuilder()
    }

    public JobBuilder getJobBuilder(){
        return this.jobBuilder;
    }
}
