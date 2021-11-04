package io.github.msengbusch.murraycraft.cloud.api.old.task

interface Task {
    val name: String

    fun dependsOn(obj: Any)

    fun run()
}