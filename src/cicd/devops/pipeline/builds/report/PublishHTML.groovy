package cicd.devops.pipeline.builds.report

class PublishHTML {

    public String reportDir
    private String reportFiles
    private String reportName

    PublishHTML(String reportDir, String reportFiles, String reportName) {
        this.reportDir = reportDir
        this.reportFiles = reportFiles
        this.reportName = reportName
    }

    String getReportDir(){
        reportDir
    }

    String getReportFiles(){
        reportFiles
    }

    Object targetPublishObject() {
        return [
                allowMissing         : false,
                alwaysLinkToLastBuild: false,
                keepAll              : true,
                reportDir            : "${reportDir}",
                reportFiles          : "${reportFiles}",
                reportName           : "${reportName}"
        ]
    }
}
