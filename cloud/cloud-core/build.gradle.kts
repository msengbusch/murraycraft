plugins {
    `config-kotlin`
}

dependencies {
    // Cloud Api
    implementation(project(":cloud:cloud-api"))

    // Logging
    implementation("org.tinylog:tinylog-impl:2.3.2")
    implementation("org.tinylog:tinylog-api-kotlin:2.3.2")

    // TOML
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-toml:2.13.0")

    // Corutines
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    // Scripting
    //implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.5.31")
    //implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.5.31")
    //implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:1.5.31")
}
