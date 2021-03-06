package io.github.msengbusch.murraycraft.cloud.old

import io.github.msengbusch.murraycraft.cloud.api.old.ICloud
import io.github.msengbusch.murraycraft.cloud.old.plugin.PluginManager
import io.github.msengbusch.murraycraft.cloud.old.template.TemplateManager
import org.tinylog.kotlin.Logger

class Cloud : ICloud {
    private var running = true

    val pluginManager: PluginManager = PluginManager(this)
    val templateManager: TemplateManager = TemplateManager()

    fun run() {
        init()
        loop()
        shutdown()
    }

    override fun stop() {
        Logger.info("Stopping")
        running = false
    }

    private fun init() {
        Logger.info("Starting")

        pluginManager.loadPlugins()
        pluginManager.initPlugins()

        templateManager.loadTemplates()
    }

    private fun loop() {
        Logger.info("Started")

        while (running) {
            Thread.sleep(1000)
            stop()
        }
    }

    private fun shutdown() {
        Logger.info("Shutting down")

        pluginManager.shutdownPlugins()
    }
}