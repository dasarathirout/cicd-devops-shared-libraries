package cicd.devops.pipeline.parameter

class JobParameters {

    private JobParameters jobParameters;

    JobParameters(){
        println("Job Parameters")
    }

    JobParameters getJobParameters(){
        return this.jobParameters
    }
}
