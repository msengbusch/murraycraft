package io.github.msengbusch.murraycraft.cloud.old.template

import io.github.msengbusch.murraycraft.cloud.old.template.load.TemplateLoader

class TemplateManager {
    private val templates: MutableMap<String, Template> = mutableMapOf()

    fun loadTemplates() {
        val loader = TemplateLoader()
        templates.putAll(loader.loadTemplates())
    }


}