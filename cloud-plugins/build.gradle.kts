plugins {
    `config-kotlin`
}

subprojects {
    dependencies {
        implementation(project(":cloud-api"))
    }
}