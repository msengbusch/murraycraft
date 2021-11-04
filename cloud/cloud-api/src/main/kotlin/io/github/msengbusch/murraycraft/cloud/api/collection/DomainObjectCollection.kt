package io.github.msengbusch.murraycraft.cloud.api.collection

import io.github.msengbusch.murraycraft.cloud.api.base.Action
import io.github.msengbusch.murraycraft.cloud.api.base.Spec
import io.github.msengbusch.murraycraft.cloud.api.provider.Provider

interface DomainObjectCollection<T> : MutableCollection<T> {
    //fun addLater(provider: Provider<T>)
    //fun addAllLater(provider: Provider<Iterable<T>>)

    fun <S> withType(type: Class<S>): DomainObjectCollection<S> where S: T
    fun <S> withType(type: Class<S>, configureAction: Action<S>): DomainObjectCollection<S> where S: T

    //fun matching(spec: Spec<T>): DomainObjectCollection<T>

    //fun all(action: Action<T>)

    //fun whenObjectAdded(action: Action<T>)
    //fun whenObjectRemoved(action: Action<T>)

    //fun configureEach(action: Action<T>)
}