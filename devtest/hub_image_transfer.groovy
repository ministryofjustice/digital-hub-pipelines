def job_script = readFileFromWorkspace("seedJob","devtest/hub_image_transfer.pipeline_script")


pipelineJob("hub_image_transfer") {
    description("Copy hub docker images to hub hosts")
      logRotator {
        numToKeep(10)
      }

      parameters {
      choiceParam('Prison', ['Staging', 'Berwyn', 'Wayland'])
      choiceParam('Component', ['Frontend', 'CMS', 'DB', 'Stats'])
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