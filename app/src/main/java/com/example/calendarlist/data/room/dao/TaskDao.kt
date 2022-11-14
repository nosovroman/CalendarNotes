package com.example.calendarlist.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calendarlist.data.room.entities.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity WHERE day = :day")
    suspend fun getDayTasks(day: String): List<TaskEntity>
    //fun getDayTasks(day: String): LiveData<List<TaskEntity>>

    @Insert
    suspend fun saveTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity
}