package cicd.devops.pipeline.parameter

class JobParameters {

    private JobParameters jobParameters;

    JobParameters(){
        println("Job Parameters")
    }

    def run = { ->
        println("Job Parameters Run")
    }
}
