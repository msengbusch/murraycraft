package io.github.msengbusch.murraycraft.cloud.template.load

class TemplateDefinition {
    var extends: List<String>? = null

    var port: Int? = null
    var allowPortRewrite: Boolean? = null

    var minServiceCount: Int? = null
    var maxServiceCount: Int? = null
}