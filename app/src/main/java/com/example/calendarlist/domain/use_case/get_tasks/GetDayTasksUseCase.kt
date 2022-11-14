package com.example.calendarlist.domain.use_case.get_tasks

import android.util.Log
import com.example.calendarlist.common.Resource
import com.example.calendarlist.data.room.entities.toTask
import com.example.calendarlist.domain.model.Task
import com.example.calendarlist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDayTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getTasks(day: String): Flow<Resource<List<Task>>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("UseCase", "111")
            val listOfTasks = repository.getDayTasks(day)//.value?.map { it.toTask() } ?: emptyList()
            Log.d("UseCase", "2 $listOfTasks")
            emit(Resource.Success(listOfTasks.map { it.toTask() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}