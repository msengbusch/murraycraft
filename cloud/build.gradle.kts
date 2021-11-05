tasks {
    create<Copy>("copyPlugins") {
        into("run/modules")

        project(":cloud:cloud-modules").subprojects
            .filter { !it.name.endsWith("api") }
            .forEach {
                with(it) {
                    afterEvaluate {
                        from(tasks.getByName("jar"))
                    }
                }
            }
    }
}