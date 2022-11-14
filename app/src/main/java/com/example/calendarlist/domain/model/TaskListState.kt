package com.example.calendarlist.domain.model

import com.example.calendarlist.domain.model.Task

data class TaskListState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String = ""
)
