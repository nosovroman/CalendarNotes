package com.example.calendarlist.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calendarlist.domain.model.Task
import com.example.calendarlist.domain.model.TaskDetail
import java.sql.Timestamp

@Entity
data class TaskEntity(
    @ColumnInfo(name = "date_start") val dateStart: Int,
    val name: String,
    val description: String,
    val day: String,

    @PrimaryKey(autoGenerate = true) var id: Int? = null
)

fun TaskEntity.toTask() : Task {
    return Task(
        id = id!!,
        dateStart = dateStart,
        name = name
    )
}

fun TaskEntity.toTaskDetail() : TaskDetail {
    return TaskDetail(
        id = id!!,
        dateStart = dateStart,
        name = name,
        description = description,
        day = day
    )
}