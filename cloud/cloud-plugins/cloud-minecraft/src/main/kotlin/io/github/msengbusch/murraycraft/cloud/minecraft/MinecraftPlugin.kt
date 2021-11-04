package io.github.msengbusch.murraycraft.cloud.minecraft

import io.github.msengbusch.murraycraft.cloud.api.old.plugin.IPlugin

class MinecraftPlugin : IPlugin {
    override fun init() {
        println("Hi")
    }

    override fun shutdown() {
        println("Bye")
    }
}