rootProject.name = "murraycraft"

include("cloud")
include("cloud:cloud-api", "cloud:cloud-core", "cloud:cloud-lib")
include("cloud:cloud-plugins", "cloud:cloud-plugins:cloud-minecraft")

include("proxy-plugins", "proxy-plugins:proxy-core")
include("server-plugins", "server-plugins:server-core")