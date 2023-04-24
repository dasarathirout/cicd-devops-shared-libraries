package cicd.devops.pipeline.builds

class JobBuilder {

    private JobBuilder jobBuilder;

    JobBuilder(){
        println("Job Builder")
    }

    def run = { ->
        println("Job Builder Run")
    }
}
