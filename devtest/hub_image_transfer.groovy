def job_script = readFileFromWorkspace("seedJob","devtest/hub_image_transfer.pipeline_script")

pipelineJob("hub_image_transfer") {
    description("Copy hub docker images to hub hosts")
    logRotator {
        numToKeep(10)
    }
    concurrentBuild(false)
    parameters {
    	    choice(name: 'Prison', choices: ['Staging', 'Berwyn', 'Wayland'], description: 'Choose a site to deploy the hub to')
       	    choice(name: 'Image', choices: ['Frontend', 'CMS', 'DB', 'Stats'], description: 'Choose a component to upgrade')
        }
    env {
		
    	
    }
    definition {
        cps {
            script(job_script)
            sandbox()
        }
    }
}
