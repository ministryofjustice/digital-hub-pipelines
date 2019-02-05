def job_script = readFileFromWorkspace("seedJob","devtest/hub_docker_compose.pipeline_script")

pipelineJob("Release new software in a Prison") {
	description("Copy hub docker images to hub hosts")
	logRotator {
		numToKeep(10)
	}

	parameters {
		choiceParam('Prison', ['Staging', 'Berwyn', 'Wayland'])
	}
	definition {
		cps {
			sandbox()
			script(job_script)
		}
	}
}