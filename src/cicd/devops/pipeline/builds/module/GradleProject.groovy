package cicd.devops.pipeline.builds.module

import cicd.devops.pipeline.builds.JenkinsPipeline
import cicd.devops.pipeline.builds.parameter.JenkinsParameters

class GradleProject {

    private ArrayList<String> subModules;

    JenkinsPipeline jenkinsPipeline
    JenkinsParameters jenkinsParameters

    GradleProject(JenkinsPipeline jenkinsPipeline, JenkinsParameters jenkinsParameters){
        this.jenkinsPipeline = jenkinsPipeline
        this.jenkinsParameters = jenkinsParameters
        subModules = new ArrayList<>()
    }

    ArrayList<String> getSubModules(){
        String gradleProjects = jenkinsPipeline.shellOutput("gradle projects")
        return subModules
    }
}
