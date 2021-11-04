package io.github.msengbusch.murraycraft.cloud

import io.github.msengbusch.murraycraft.cloud.script.execute.DefaultScriptDefinition
import java.io.File
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

fun main() {
    val scriptDefinition = createJvmCompilationConfigurationFromTemplate<DefaultScriptDefinition>()

    BasicJvmScriptingHost().eval(File("scripts/main.module.kts").toScriptSource(), scriptDefinition, null)
}