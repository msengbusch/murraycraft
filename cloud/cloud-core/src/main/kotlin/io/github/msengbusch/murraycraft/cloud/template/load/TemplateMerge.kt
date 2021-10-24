package io.github.msengbusch.murraycraft.cloud.template.load

object TemplateMerge {
    fun mergeTemplates(first: TemplateDefinition, second: TemplateDefinition): TemplateDefinition {
        if(first.port != null) {
            second.port = first.port
        }

        if(first.allowPortRewrite != null) {
            second.allowPortRewrite = first.allowPortRewrite
        }

        if(first.minServiceCount != null) {
            second.minServiceCount = first.minServiceCount
        }

        if(first.maxServiceCount != null) {
            second.maxServiceCount = first.maxServiceCount
        }

        return second
    }
}