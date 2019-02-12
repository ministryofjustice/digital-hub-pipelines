def job_script = readFileFromWorkspace("seedJob","devtest/hub_secrets/hub_secrets.pipeline_script")


pipelineJob("Transfer sensitive credentials to a prison") {
    description("Update secrets in a prison")
      logRotator {
        numToKeep(10)
      }

      parameters {
      choiceParam('Prison', ['Staging', 'Berwyn', 'Wayland'])
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
}
