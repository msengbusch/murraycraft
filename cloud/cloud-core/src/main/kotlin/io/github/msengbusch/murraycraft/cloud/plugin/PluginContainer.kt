package io.github.msengbusch.murraycraft.cloud.plugin

import io.github.msengbusch.murraycraft.cloud.api.plugin.IPlugin
import io.github.msengbusch.murraycraft.cloud.api.plugin.IPluginContainer

class PluginContainer(override val name: String, override val mainClass: Class<*>,
                         override val classLoader: ClassLoader, override val instance: IPlugin) : IPluginContainer {
}