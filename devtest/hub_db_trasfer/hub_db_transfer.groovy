def job_script = readFileFromWorkspace("seedJob","devtest/hub_db_trasfer/hub_db_trasfer.pipeline_script")


pipelineJob("View Container Logs") {
  description("View running container logs")

  logRotator {
    numToKeep(4)
  }

  parameters {
    choiceParam('Site', ['Staging', 'Berwyn', 'Wayland'])
    choiceParam('Container', ['hub-node', 'hub-be'])
    booleanParam('Tail', false)
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
