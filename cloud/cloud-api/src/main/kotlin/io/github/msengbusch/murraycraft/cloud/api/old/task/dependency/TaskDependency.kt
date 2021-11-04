package io.github.msengbusch.murraycraft.cloud.api.old.task.dependency

import io.github.msengbusch.murraycraft.cloud.api.old.task.Task

interface TaskDependency {
    fun getDependencies(task: Task): Set<Task>
}