def job_script = readFileFromWorkspace("seedJob","devtest/hub_image_transfer.pipeline_script")

pipelineJob("hub_image_transfer") {
    description("Copy hub docker images to hub hosts")
    logRotator {
        numToKeep(10)
    }
    configure { Node project ->
//    project / authToken("start_cdecopy")
    }
    concurrentBuild(false)
    parameters {
        stringParam('hostname', '', 'Deployment host')
    }
    definition {
        cps {
            script(job_script)
            sandbox()
        }
    }
}
