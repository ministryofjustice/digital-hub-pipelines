def job_script = readFileFromWorkspace("seedJob","devtest/hub_logs/hub_logs.pipeline_script")


pipelineJob("Container Logs") {
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
