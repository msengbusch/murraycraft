package io.github.msengbusch.murraycraft.cloud.api.task

import io.github.msengbusch.murraycraft.cloud.api.base.Action
import io.github.msengbusch.murraycraft.cloud.api.collection.named.PolymorphicDomainObjectContainer

interface TaskContainer: PolymorphicDomainObjectContainer<Task> {
    override fun register(name: String): TaskProvider<Task>
    override fun register(name: String, configureAction: Action<Task>): TaskProvider<Task>
    override fun <U: Task> register(name: String, type: Class<U>): TaskProvider<U>
    override fun <U: Task> register(name: String, type: Class<U>, configureAction: Action<U>): TaskProvider<U>
}