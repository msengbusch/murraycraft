package io.github.msengbusch.murraycraft.cloud.old.task

import io.github.msengbusch.murraycraft.cloud.api.old.task.TaskState

class TaskStateInternal : TaskState {
    private var executing = false
    private var failure: Throwable? = null

    var outcome: TaskExecutionOutcome? = null
    internal set(value) {
        if(outcome != null) {
            throw IllegalStateException("Task state has already received its task outcome")
        }

        field = value
    }

    override fun getExecuted(): Boolean = outcome != null
    override fun getExecuting(): Boolean = executing
    override fun getFailed(): Boolean = failure != null
    override fun getSkipped(): Boolean = outcome != null && outcome!!.skipped
    override fun getUpToDate(): Boolean = outcome != null && outcome!!.upToDate
    override fun getFailure(): Throwable? = failure

    fun setExecuting(executing: Boolean) {
        this.executing = executing
    }

    fun setFailure(failure: Throwable) {
        this.outcome = TaskExecutionOutcome.FAILED
        this.failure = failure
    }
}