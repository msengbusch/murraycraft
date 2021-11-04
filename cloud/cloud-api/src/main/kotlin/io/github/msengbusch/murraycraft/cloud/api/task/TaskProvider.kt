package io.github.msengbusch.murraycraft.cloud.api.task

import io.github.msengbusch.murraycraft.cloud.api.collection.named.NamedDomainObjectProvider

interface TaskProvider<T>: NamedDomainObjectProvider<T> where T: Task {
}