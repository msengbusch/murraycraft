package io.github.msengbusch.murraycraft.cloud.api.collection.named

import io.github.msengbusch.murraycraft.cloud.api.base.Action
import io.github.msengbusch.murraycraft.cloud.api.collection.DomainObjectCollection
import java.util.*

interface NamedDomainObjectCollection<T> : DomainObjectCollection<T> {
    fun getAsMap(): SortedMap<String, T>
    fun getNames(): SortedSet<String>

    fun findByName(name: String): T?
    fun getByName(name: String): T
    fun getByName(name: String, configureAction: Action<T>): T

    fun named(name: String): NamedDomainObjectProvider<T>
    fun named(name: String, configureAction: Action<T>): NamedDomainObjectProvider<T>

    fun <S> named(name: String, type: Class<S>): NamedDomainObjectProvider<S> where S: T
    fun <S> named(name: String, type: Class<S>, configureAction: Action<S>): NamedDomainObjectProvider<S> where S: T
}