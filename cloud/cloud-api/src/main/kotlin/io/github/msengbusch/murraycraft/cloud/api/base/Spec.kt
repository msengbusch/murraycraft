package io.github.msengbusch.murraycraft.cloud.api.base

interface Spec<T> {
    fun isSatisfiedBy(element: T): Boolean
}