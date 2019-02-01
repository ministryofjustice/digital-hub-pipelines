def job_script = readFileFromWorkspace("seedJob","devtest/hub_asset_transfer.pipeline_script")


pipelineJob("Transfer new assets from Staging to a prison before a release") {
    description("Copy assets between sites")
      logRotator {
        numToKeep(10)
      }

      parameters {
      choiceParam('Prison', ['Berwyn', 'Wayland'])
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