package io.github.msengbusch.murraycraft.cloud.api.old.task

interface TaskState {
    fun getExecuted(): Boolean
    fun getExecuting(): Boolean
    fun getFailed(): Boolean
    fun getSkipped(): Boolean // Task was excluded, output or input my not be up to date
    fun getUpToDate(): Boolean // No inputs changed

    fun getFailure(): Throwable?
}