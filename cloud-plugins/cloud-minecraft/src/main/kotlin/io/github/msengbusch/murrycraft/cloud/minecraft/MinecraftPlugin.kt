package io.github.msengbusch.murrycraft.cloud.minecraft

import io.github.msengbusch.murraycraft.cloud.api.plugin.IPlugin

class MinecraftPlugin : IPlugin {
    override fun init() {
        println("Hi")
    }

    override fun shutdown() {
        println("Bye")
    }
}