package io.github.msengbusch.murraycraft.cloud.old.task

enum class TaskExecutionOutcome(val skipped: Boolean, val upToDate: Boolean, val message: String) {
    UP_TO_DATE(true, true, "UP-TO-DATE"),
    SKIPPED(true, false, "SKIPPED"),
    EXECUTED(false, false, "EXECUTED"),
    FAILED(true, false, "FAILED")
}