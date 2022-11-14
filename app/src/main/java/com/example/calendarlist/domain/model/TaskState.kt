package com.example.calendarlist.domain.model

import com.example.calendarlist.domain.model.TaskDetail

data class TaskState(
    val isLoading: Boolean = false,
    val task: TaskDetail? = null,
    val error: String = ""
)
