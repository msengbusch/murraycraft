package io.github.msengbusch.murraycraft.cloud.old.task.dependency

import io.github.msengbusch.murraycraft.cloud.api.old.task.Task
import io.github.msengbusch.murraycraft.cloud.api.old.task.dependency.TaskDependencyResolveContext

class TaskDependencyResolveContextImpl : TaskDependencyResolveContext {
    private val queue = ArrayDeque<Any>()
    //private val walker = CachingDirectedGraphWalker<Any, Task>()

    override fun add(dependency: Any) {
        TODO("Not yet implemented")
    }

    override fun getTask(): Task {
        TODO("Not yet implemented")
    }
}