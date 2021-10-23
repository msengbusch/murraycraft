package io.github.msengbusch.murraycraft.cloud.plugin

import io.github.msengbusch.murraycraft.cloud.api.ICloud
import io.github.msengbusch.murraycraft.cloud.api.plugin.IPlugin
import io.github.msengbusch.murraycraft.cloud.api.plugin.IPluginManager
import org.tinylog.kotlin.Logger
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.name

class PluginManager(private val cloud: ICloud) : IPluginManager {
    private val plugins: MutableMap<String, PluginContainer> = mutableMapOf()

    fun initPlugins() {
        plugins.forEach { (_, container) -> container.instance.init() }
    }

    fun shutdownPlugins() {
        plugins.forEach { (_, container) -> container.instance.shutdown() }
    }

    fun loadPlugins() {
        val pluginDir = Path(PLUGIN_DIR)

        Files.list(pluginDir).forEach {
            if(!it.name.endsWith(".jar")) {
                return@forEach
            }

            try {
                loadPlugin(it)
            } catch (e: RuntimeException) {
                Logger.error(e, "Failed to load plugin {}", it)
            }
        }
    }

    private fun loadPlugin(jar: Path) {
        Logger.debug("Load plugin from jar {}", jar)

        val classLoader = URLClassLoader(arrayOf(jar.toUri().toURL()))
        val propertiesResource = classLoader.getResource("cloud-plugin.properties")
            ?: throw PluginException("Failed to find cloud-plugin.properties")

        val pluginProperties = Properties()
        pluginProperties.load(propertiesResource.openStream())

        if(!pluginProperties.containsKey("name")) {
            throw PluginException("Failed to read name from cloud-plugin.properties: $jar")
        }

        if(!pluginProperties.containsKey("main")) {
            throw PluginException("Failed to read main class from cloud-plugin.properties: $jar")
        }

        val name = pluginProperties.getProperty("name")
        val mainClassName = pluginProperties.getProperty("main")

        if(plugins.containsKey(name)) {
            throw PluginException("Plugin $name is already registered")
        }

        val mainClass = classLoader.loadClass(mainClassName)

        if(!IPlugin::class.java.isAssignableFrom(mainClass)) {
            throw PluginException("Plugin $name main class does not extends IPlugin")
        }

        val constructors = mainClass.constructors
            .filter { it.parameterTypes.isEmpty() || it.parameterTypes.contains(ICloud::class.java) }

        if(constructors.isEmpty()) {
            throw PluginException("Plugin $name no suitable constructor found")
        }

        if(constructors.size > 1) {
            throw PluginException("Plugin $name more than one suitable constructor found")
        }

        val constructor = constructors[0]
        val instance = if(constructor.parameterTypes.contains(ICloud::class.java)) {
            constructor.newInstance(cloud)
        } else {
            constructor.newInstance()
        } as IPlugin

        val container = PluginContainer(name, mainClass, classLoader, instance)
        plugins[name] = container
    }

    companion object {
        const val PLUGIN_DIR = "plugins"
    }
}