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
        jvm {
            val cacheBaseDir = File("cache")
            if(!cacheBaseDir.exists()) {
                cacheBaseDir.mkdirs()
            }

            compilationCache(
                CompiledScriptJarsCache { script, scriptCompilationConfiguration ->
                    File(cacheBaseDir, compiledScriptUniqueName(script, scriptCompilationConfiguration) + ".jar")
                }
            )
        }
    })
})

private fun compiledScriptUniqueName(script: SourceCode, scriptCompilationConfiguration: ScriptCompilationConfiguration): String {
    val digest = MessageDigest.getInstance("MD5")
    digest.update(script.text.toByteArray())
    scriptCompilationConfiguration.notTransientData.entries
        .sortedBy { it.key.name }
        .forEach {
            digest.update(it.key.name.toByteArray())
            digest.update(it.value.toString().toByteArray())
        }
    return digest.digest().toHexString()
}

private fun ByteArray.toHexString(): String = joinToString("", transform = { "%02x".format(it) })