package cicd.devops.pipeline.parameter

class JobParameters {

    private JobParameters jobParameters;
    public JobParameters(){
        println("Job Parameters")
        jobParameters= new JobParameters()
    }

    public JobParameters getJobParameters(){
        return this.jobParameters
    }
}
