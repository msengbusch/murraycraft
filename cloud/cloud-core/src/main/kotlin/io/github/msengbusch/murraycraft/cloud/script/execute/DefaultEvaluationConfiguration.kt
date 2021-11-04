package io.github.msengbusch.murraycraft.cloud.script.execute

import kotlin.script.experimental.api.ScriptEvaluationConfiguration
import kotlin.script.experimental.api.implicitReceivers
import kotlin.script.experimental.api.scriptsInstancesSharing

class DefaultEvaluationConfiguration : ScriptEvaluationConfiguration({
    scriptsInstancesSharing(true)
})