package com.example.calendarlist.data.repository

import androidx.lifecycle.LiveData
import com.example.calendarlist.data.room.dao.TaskDao
import com.example.calendarlist.data.room.entities.TaskEntity
import com.example.calendarlist.domain.repository.TaskRepository

class TaskRepositoryImpl (private val taskDao: TaskDao): TaskRepository {
    //override fun getDayTasks(day: String) = taskDao.getDayTasks(day)
    override suspend fun getDayTasks(day: String) = taskDao.getDayTasks(day)
    override suspend fun saveTask(taskEntity: TaskEntity) {
        taskDao.saveTask(taskEntity)
    }


    //override fun saveTask(taskEntity: TaskEntity) = taskDao.saveTask(taskEntity)

    override suspend fun getTaskById(id: Int) = taskDao.getTaskById(id)
}