rootProject.name = "murraycraft"

include("cloud")
include("cloud:cloud-api", "cloud:cloud-core", "cloud:cloud-lib", "cloud:run")

// Cloud Modules
include("cloud:cloud-modules")
include("cloud:cloud-modules:cloud-minecraft", "cloud:cloud-modules:cloud-minecraft:api")
include("cloud:cloud-modules:cloud-repl", "cloud:cloud-modules:cloud-repl:api")
include("cloud:cloud-modules:cloud-rest", "cloud:cloud-modules:cloud-rest:api")
include("cloud:cloud-modules:cloud-service", "cloud:cloud-modules:cloud-service:api")
include("cloud:cloud-modules:cloud-template", "cloud:cloud-modules:cloud-template:api")

include("proxy-plugins", "proxy-plugins:proxy-core")
include("server-plugins", "server-plugins:server-core")