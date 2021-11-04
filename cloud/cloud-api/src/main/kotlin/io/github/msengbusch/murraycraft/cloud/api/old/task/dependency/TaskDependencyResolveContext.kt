package io.github.msengbusch.murraycraft.cloud.api.old.task.dependency

import io.github.msengbusch.murraycraft.cloud.api.old.task.Task

interface TaskDependencyResolveContext {
    fun add(dependency: Any)

    //fun visitFailure(failure: Throwable)

    fun getTask(): Task
}