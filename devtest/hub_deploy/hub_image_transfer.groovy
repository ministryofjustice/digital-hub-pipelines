def job_script = readFileFromWorkspace("seedJob","devtest/hub_deploy/hub_image_transfer.pipeline_script")


pipelineJob("Transfer new docker images to a prison before a release") {
    description("Copy hub docker images to hub hosts")
      logRotator {
        numToKeep(10)
      }

      parameters {
      choiceParam('Prison', ['Staging', 'Berwyn', 'Wayland'])
      choiceParam('Component', ['Frontend', 'CMS', 'DB', 'Matomo', 'Stats DB', 'Elasticsearch', 'Nginx'])
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
