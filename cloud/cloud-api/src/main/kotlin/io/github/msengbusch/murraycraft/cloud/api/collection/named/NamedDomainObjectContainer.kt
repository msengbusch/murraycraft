package io.github.msengbusch.murraycraft.cloud.api.collection.named

import io.github.msengbusch.murraycraft.cloud.api.base.Action

interface NamedDomainObjectContainer<T>: NamedDomainObjectSet<T>, Action<NamedDomainObjectContainer<T>> {
    fun create(name: String): T
    fun create(name: String, configureAction: Action<T>): T

    fun createMaybe(name: String): T

    fun register(name: String): NamedDomainObjectProvider<T>
    fun register(name: String, configureAction: Action<T>): NamedDomainObjectProvider<T>
}