package com.example.calendarlist.domain.model

import com.example.calendarlist.data.room.entities.TaskEntity

data class TaskDetail (
    val dateStart: Int,
    val name: String,
    val description: String,
    val day: String,
    val id: Int? = null
)

fun TaskDetail.toTaskEntity() : TaskEntity {
    if (id == null) {
        return TaskEntity(
            dateStart = dateStart,
            name = name,
            description = description,
            day = day
        )
    }
    return TaskEntity(
        dateStart = dateStart,
        name = name,
        description = description,
        day = day,
        id = id
    )
}