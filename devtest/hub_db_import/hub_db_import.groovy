def job_script = readFileFromWorkspace("seedJob","devtest/hub_db_import/hub_db_import.pipeline_script")


pipelineJob("Import Production Database") {
  description("Import a previously transferred production database")

  logRotator {
    numToKeep(4)
  }

  parameters {
    choiceParam('Site', ['Berwyn', 'Wayland'])
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
