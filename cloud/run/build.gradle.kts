plugins {
    `config-kotlin`
}

sourceSets {
    main {
        java {
            srcDir("scripts")
        }
    }
}

dependencies {
    implementation(project(":cloud:cloud-core"))
    implementation(kotlin("script-runtime"))
}