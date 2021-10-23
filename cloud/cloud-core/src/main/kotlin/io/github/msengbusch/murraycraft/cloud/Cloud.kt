package io.github.msengbusch.murraycraft.cloud

import io.github.msengbusch.murraycraft.cloud.api.ICloud
import io.github.msengbusch.murraycraft.cloud.plugin.PluginManager
import org.tinylog.kotlin.Logger

class Cloud : ICloud {
    private var running = true

    val pluginManager: PluginManager = PluginManager(this)

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