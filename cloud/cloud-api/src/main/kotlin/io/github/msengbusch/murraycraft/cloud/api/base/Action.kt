package io.github.msengbusch.murraycraft.cloud.api.base

interface Action<T> {
    fun execute(t: T)
}