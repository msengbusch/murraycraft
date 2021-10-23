plugins {
    `config-kotlin`
}

dependencies {
    // Cloud Api
    implementation(project(":cloud-api"))

    // Logging
    implementation("org.tinylog:tinylog-impl:2.3.2")
    implementation("org.tinylog:tinylog-api-kotlin:2.3.2")

    // Corutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    // Scripting
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:1.5.31")
}

tasks {
   create<Copy>("copyPlugins") {
        into("run/plugins")

       project(":cloud-plugins").subprojects {
           //dependsOn(this)
           afterEvaluate {
               dependsOn(tasks.getByName("jar"))
               from(tasks.getByName("jar"))
           }
       }

        //dependsOn(":cloud-plugins:cloud-minecraft:jar")
        //from(project(":cloud-plugins:cloud-minecraft").task)

        /*project(":cloud-plugins").subprojects.forEach {
            //dependsOn(subproject)
            //dependsOn(":cloud-plugins:${subproject.name}:jar")
            //from(":cloud-plugins:${subproject.name}:jar")
            //subproject.afterEvaluate {
                from(it.tasks.named("jar"))
            //}
        }*/
    }
}