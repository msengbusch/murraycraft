tasks {
   create<Copy>("copyPlugins") {
        into("run/plugins")

       project(":cloud:cloud-plugins").subprojects {
           afterEvaluate {
               from(tasks.getByName("jar"))
           }
       }
    }
}