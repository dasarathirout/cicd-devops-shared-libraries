package cicd.devops.pipeline.builds

class JobBuilder {

    private JobBuilder jobBuilder;

    JobBuilder(){
        println("Job Builder")
    }

    JobBuilder getJobBuilder(){
        return this.jobBuilder;
    }
}
