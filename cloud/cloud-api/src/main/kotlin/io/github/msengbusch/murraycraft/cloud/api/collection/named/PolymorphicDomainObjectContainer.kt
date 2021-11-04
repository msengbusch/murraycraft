package io.github.msengbusch.murraycraft.cloud.api.collection.named

import io.github.msengbusch.murraycraft.cloud.api.base.Action

interface PolymorphicDomainObjectContainer<T>: NamedDomainObjectContainer<T> {
    fun <U: T> create(name: String, type: Class<U>): U
    fun <U: T> create(name: String, type: Class<U>, configureAction: Action<U>): U

    fun <U: T> createMaybe(name: String, type: Class<U>): U

    fun <U: T> containerWithType(type: Class<U>): NamedDomainObjectContainer<U>

    fun <U: T> register(name: String, type: Class<U>): NamedDomainObjectProvider<U>
    fun <U: T> register(name: String, type: Class<U>, configureAction: Action<U>): NamedDomainObjectProvider<U>
}