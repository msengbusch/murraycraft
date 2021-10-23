package io.github.msengbusch.murraycraft.cloud

import io.github.msengbusch.murraycraft.cloud.api.ICloud
import org.tinylog.kotlin.Logger

class Cloud : ICloud {
    private var running = true

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
    }
}