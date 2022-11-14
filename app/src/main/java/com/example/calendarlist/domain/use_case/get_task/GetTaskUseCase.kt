package com.example.calendarlist.domain.use_case.get_task

import android.util.Log
import com.example.calendarlist.common.Resource
import com.example.calendarlist.data.room.entities.toTask
import com.example.calendarlist.data.room.entities.toTaskDetail
import com.example.calendarlist.domain.model.Task
import com.example.calendarlist.domain.model.TaskDetail
import com.example.calendarlist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getTaskById(id: Int): Flow<Resource<TaskDetail>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("UseCase", "111")
            val currentTask = repository.getTaskById(id)
            Log.d("UseCase", "2 $currentTask")
            emit(Resource.Success(currentTask.toTaskDetail()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}