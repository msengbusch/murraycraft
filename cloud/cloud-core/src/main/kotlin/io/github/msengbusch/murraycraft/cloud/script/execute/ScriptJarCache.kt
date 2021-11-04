package io.github.msengbusch.murraycraft.cloud.script.execute

import java.io.File
import java.security.MessageDigest
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.host.ScriptingHostConfiguration
import kotlin.script.experimental.jvm.compilationCache
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.CompiledScriptJarsCache

fun ScriptingHostConfiguration.Builder.withJarScriptCache(cacheBaseDir: File) {
    if(!cacheBaseDir.exists()) {
        cacheBaseDir.mkdirs()
    }

    jvm {
        compilationCache(CompiledScriptJarsCache { script, compilationConfiguration ->
            File(cacheBaseDir, compiledScriptUniqueName(script, compilationConfiguration) + ".jar")
        })
    }
}

private fun compiledScriptUniqueName(script: SourceCode, compilationConfiguration: ScriptCompilationConfiguration): String {
    val digest = MessageDigest.getInstance("MD5")
    digest.update(script.text.toByteArray())
    compilationConfiguration.notTransientData.entries
        .sortedBy { it.key.name }
        .forEach {
            digest.update(it.key.name.toByteArray())
            digest.update(it.value.toString().toByteArray())
        }
    return digest.digest().toHexString()
}

private fun ByteArray.toHexString(): String = joinToString("", transform = { "%02x".format(it) })