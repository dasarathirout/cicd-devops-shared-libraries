import cicd.devops.*

def call(Closure body){
    println(body)
    startJobBuild('projectName')
}

def startJobBuild(String projectName) {
    timestamps{
        println('#####################')
        new DriverMain().init()
        println('=====================')
    }
}