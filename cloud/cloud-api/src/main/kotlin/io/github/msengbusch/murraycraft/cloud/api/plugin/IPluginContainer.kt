package io.github.msengbusch.murraycraft.cloud.api.plugin

interface IPluginContainer {
    val name: String
    val mainClass: Class<*>
    val classLoader: ClassLoader
    val instance: Any
}