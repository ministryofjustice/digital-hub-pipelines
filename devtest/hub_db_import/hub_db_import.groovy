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
  if (System.getenv('AZURE_AD_DEV_GROUP') != null) {
    authorization {
      permissions(System.getenv('AZURE_AD_DEV_GROUP'), [
        //'com.cloudbees.plugins.credentials.CredentialsProvider.Create',
        //'com.cloudbees.plugins.credentials.CredentialsProvider.Delete',
        //'com.cloudbees.plugins.credentials.CredentialsProvider.ManageDomains',
        //'com.cloudbees.plugins.credentials.CredentialsProvider.Update',
        //'com.cloudbees.plugins.credentials.CredentialsProvider.View',
        'hudson.model.Item.Build',
        'hudson.model.Item.Cancel',
        //'hudson.model.Item.Configure',
        //'hudson.model.Item.Delete',
        //'hudson.model.Item.Discover',
        //'hudson.model.Item.Move',
        'hudson.model.Item.Read',
        //'hudson.model.Item.Workspace',
        //'hudson.model.Run.Delete',
        'hudson.model.Run.Replay',
        //'hudson.model.Run.Update',
        //'hudson.scm.SCM.Tag'
      ])
      blocksInheritance()
    }
  }
}
