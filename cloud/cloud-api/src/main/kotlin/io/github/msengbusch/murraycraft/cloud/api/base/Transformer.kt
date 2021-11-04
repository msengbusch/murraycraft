package io.github.msengbusch.murraycraft.cloud.api.base

interface Transformer<I, O> {
    fun transform(i: I): O
}