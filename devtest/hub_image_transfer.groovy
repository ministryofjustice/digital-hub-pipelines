def job_script = readFileFromWorkspace("seedJob","devtest/hub_image_transfer.pipeline_script")

pipelineJob("hub_image_transfer") {
    description("Copy hub docker images to hub hosts")
    logRotator {
        numToKeep(10)
    }
    concurrentBuild(false)
    parameters {
//   	    choiceParam(name: 'Prison', choices: ['Staging', 'Berwyn', 'Wayland'], description: 'Choose a site to deploy the hub to')
   	    choiceParam(name: 'Image', choices: ['Frontend', 'CMS', 'DB', 'Stats'], description: 'Choose a component to upgrade')
    }


/*      parameters {
        stringParam('target_host', project.host.toUpperCase(), 'Backup target server')
        stringParam('daily_weekly', period, 'Is this a daily or weekly backup?')
        booleanParam("run_rman_backup_script", true, "Run the rman backup script")
        booleanParam("run_azcopy", project.run_azcopy, "Run the azcopy stage or not")
        booleanParam('checkMode', false, 'Check Mode')
        stringParam('monoRepoBranch', 'master', 'Ansible Monorepo Branch Name')
        stringParam('inventoryName', inventoryName, 'Inventory File')
        stringParam("extraAnsibleArgs", "--diff -v", "ansible-playbook additional parameters")
      }*/
    env {
		
    	
    }
    definition {
        cps {
            script(job_script)
            sandbox()
        }
    }
}
