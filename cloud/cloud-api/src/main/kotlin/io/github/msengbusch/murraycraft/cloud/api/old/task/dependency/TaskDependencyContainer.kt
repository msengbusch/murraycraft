package io.github.msengbusch.murraycraft.cloud.api.old.task.dependency

interface TaskDependencyContainer {
    fun visitDependencies(context: TaskDependencyResolveContext)
}