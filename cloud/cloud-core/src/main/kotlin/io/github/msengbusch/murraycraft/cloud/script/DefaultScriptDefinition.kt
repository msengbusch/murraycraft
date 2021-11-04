package io.github.msengbusch.murraycraft.cloud.script

import io.github.msengbusch.murraycraft.cloud.script.execute.DefaultCompilationConfiguration
import io.github.msengbusch.murraycraft.cloud.script.execute.DefaultEvaluationConfiguration
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "module.kts",
    compilationConfiguration = DefaultCompilationConfiguration::class,
    evaluationConfiguration = DefaultEvaluationConfiguration::class
)
abstract class DefaultScriptDefinition {
}