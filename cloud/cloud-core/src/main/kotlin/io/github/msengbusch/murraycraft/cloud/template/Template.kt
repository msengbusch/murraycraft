package io.github.msengbusch.murraycraft.cloud.template

import io.github.msengbusch.murraycraft.cloud.template.load.TemplateDefinition

class Template(val name: String, definition: TemplateDefinition) {
    val port: Int = definition.port ?: 44411
    val allowPortRewrite: Boolean = definition.allowPortRewrite ?: true
    val minServiceCount: Int = definition.minServiceCount ?: -1
    val maxServiceCount: Int = definition.maxServiceCount ?: -1
}