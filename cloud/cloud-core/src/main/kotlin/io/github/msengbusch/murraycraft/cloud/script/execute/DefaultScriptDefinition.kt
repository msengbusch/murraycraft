package io.github.msengbusch.murraycraft.cloud.script.execute

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "module.kts",
    compilationConfiguration = DefaultCompilationConfiguration::class,
    evaluationConfiguration = DefaultEvaluationConfiguration::class

)
abstract class DefaultScriptDefinition {
}