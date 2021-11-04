package io.github.msengbusch.murraycraft.cloud.api.collection.named

import io.github.msengbusch.murraycraft.cloud.api.base.Action
import io.github.msengbusch.murraycraft.cloud.api.provider.Provider

interface NamedDomainObjectProvider<T> : Provider<T> {
    fun getName(): String

    fun configure(action: Action<T>)
}