import javaposse.jobdsl.dsl.*
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.*

File workspace = new File(WORKSPACE)
String jobDirectory = "${WORKSPACE}/${SERVER_ENVIRONMENT}"

JenkinsJobManagement jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
DslScriptLoader dslLoader = new DslScriptLoader(jobManagement)

// Prevent other jobs kicking off whilst we're still building
Jenkins.instance.doQuietDown()

try {
    // Load groovy files from our jobs directory
    new FileNameFinder().getFileNames(jobDirectory, '**/*.groovy').each { dslScript ->
        println("Processing script: ${dslScript}")
        dslLoader.runScript(new File(dslScript).text)
    }

    // Disable any jobs that have SCM triggers if requested
    if ("${System.getenv('DISABLE_JOBS_ON_CREATE') ?: 'false'}".toLowerCase() == "true") {
        Jenkins.instance.items.each { job ->
            if (!job.getTriggers().isEmpty()) {
                println("Disabling job with Trigger: ${job.name}")
                job.setDisabled(true)
            }
        }
    }
} finally {
    // Re-enable job queue
    Jenkins.instance.doCancelQuietDown()
}

