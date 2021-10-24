package io.github.msengbusch.murraycraft.cloud.template.load

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.github.msengbusch.murraycraft.cloud.plugin.PluginException
import io.github.msengbusch.murraycraft.cloud.template.Template
import io.github.msengbusch.murraycraft.cloud.util.GraphNode
import io.github.msengbusch.murraycraft.cloud.util.TopologicalSort
import org.tinylog.kotlin.Logger
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.name

class TemplateLoader {
    private val jackson = TomlMapper()
        .registerModule(kotlinModule())

    fun loadTemplates(): Map<String, Template> {
        val templatesDir = Path(TEMPLATES_DIR)

        val definitions: Map<String, GraphNode<String, TemplateDefinition>> = Files.list(templatesDir)
            .filter { it.name.endsWith(".toml") }
            .map { Pair(it.fileName.toString().replace(".toml", ""), loadTemplateDefinition(it)) }
            .toList()
            .toMap()
            .mapValues { GraphNode(it.key, it.value) }

        Logger.debug("Load ${definitions.size} templates")

        definitions.forEach { (name, node) ->
            node.value.extends?.forEach {
                node.neighbours.add(definitions[it] ?: throw PluginException("Template $name extends unknown template $it"))
            }
        }

        val sortedTemplates = TopologicalSort.topologicalSort(definitions.values.toList())
        val loadedTemplates: MutableMap<String, Template> = mutableMapOf()

        for(sortedTemplate in sortedTemplates) {
            val templateName = sortedTemplate.key
            var template = sortedTemplate.value

            template.extends?.forEach {
                template = TemplateMerge.mergeTemplates(template, definitions[it]!!.value)
            }

            loadedTemplates[templateName] = Template(templateName, template)
        }

        return loadedTemplates
    }

    private fun loadTemplateDefinition(file: Path): TemplateDefinition {
        val definition = jackson.readValue(file.toFile(), TemplateDefinition::class.java)
        return definition
    }

    companion object {
        const val TEMPLATES_DIR = "templates"
    }
}