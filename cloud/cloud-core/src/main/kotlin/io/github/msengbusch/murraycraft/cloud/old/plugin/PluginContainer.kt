package io.github.msengbusch.murraycraft.cloud.old.plugin

import io.github.msengbusch.murraycraft.cloud.api.old.plugin.IPlugin
import io.github.msengbusch.murraycraft.cloud.api.old.plugin.IPluginContainer

class PluginContainer(override val name: String, override val mainClass: Class<*>,
                         override val classLoader: ClassLoader, override val instance: IPlugin
) : IPluginContainer {
}