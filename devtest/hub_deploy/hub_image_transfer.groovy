def job_script = readFileFromWorkspace("seedJob","devtest/hub_deploy/hub_image_transfer.pipeline_script")


pipelineJob("Transfer new docker images to a prison before a release") {
    description("Copy hub docker images to hub hosts")
      logRotator {
        numToKeep(10)
      }

      parameters {
      choiceParam('Prison', ['Staging', 'Berwyn', 'Wayland'])
      choiceParam('Component', ['Frontend', 'CMS', 'DB', 'Stats', 'Stats DB'])
      }
      properties {
        disableConcurrentBuilds()
      }
      definition {
        cps {
          sandbox()
          script(job_script)
        }
      }
}
