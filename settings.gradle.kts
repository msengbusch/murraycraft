rootProject.name = "murraycraft"

include("cloud")
include("cloud:cloud-api", ":cloud:cloud-core")
include("cloud:cloud-plugins", "cloud:cloud-plugins:cloud-minecraft")

include("proxy-plugins")
include("server-plugins")