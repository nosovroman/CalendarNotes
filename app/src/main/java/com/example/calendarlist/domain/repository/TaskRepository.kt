package com.example.calendarlist.domain.repository

import androidx.lifecycle.LiveData
import com.example.calendarlist.data.room.entities.TaskEntity

interface TaskRepository {
    //fun getDayTasks(day: String): LiveData<List<TaskEntity>>
    suspend fun getDayTasks(day: String): List<TaskEntity>
    suspend fun saveTask(taskEntity: TaskEntity)

    suspend fun getTaskById(id: Int): TaskEntity
}