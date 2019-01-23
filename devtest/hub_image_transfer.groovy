def job_name = "Digital Hub - transfer docker images to sites"
def slurper = new groovy.json.JsonSlurper()
def buildMatrix = readFileFromWorkspace("seedJob","${System.getenv("SERVER_ENVIRONMENT")}/hub_image_transfer.json")
def job_script = readFileFromWorkspace("seedJob","${System.getenv("SERVER_ENVIRONMENT")}/hub_image_transfer.pipeline_script")

# Stub
