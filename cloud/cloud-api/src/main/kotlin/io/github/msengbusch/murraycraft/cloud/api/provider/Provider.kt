package io.github.msengbusch.murraycraft.cloud.api.provider

import io.github.msengbusch.murraycraft.cloud.api.base.Transformer

interface Provider<T> {
    fun get(): T
    fun getOrNull(): T?
    fun getOrElse(defaultValue: T): T

    fun orElse(value: T): Provider<T>
    fun orElse(provider: Provider<T>): Provider<T>

    fun <S> map(transformer: Transformer<T, S>): Provider<S>
    fun <S> flatMap(transformer: Transformer<T, S>): Provider<S>

    fun isPresent(): Boolean
}