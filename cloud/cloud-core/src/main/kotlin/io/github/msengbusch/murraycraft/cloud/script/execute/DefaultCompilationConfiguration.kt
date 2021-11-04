package io.github.msengbusch.murraycraft.cloud.script.execute

import java.io.File
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.ScriptingHostConfiguration
import kotlin.script.experimental.jvm.compilationCache
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.CompiledScriptJarsCache

class DefaultCompilationConfiguration : ScriptCompilationConfiguration({
    jvm {
       dependenciesFromClassContext(DefaultCompilationConfiguration::class, wholeClasspath = true)
    }

    ide {
       acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }

    hostConfiguration(ScriptingHostConfiguration {
        withJarScriptCache(File("cache"))
    })
})