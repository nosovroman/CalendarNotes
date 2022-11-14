package com.example.calendarlist.domain.use_case.save_task

import android.util.Log
import com.example.calendarlist.common.Resource
import com.example.calendarlist.data.room.entities.toTask
import com.example.calendarlist.domain.model.TaskDetail
import com.example.calendarlist.domain.model.toTaskEntity
import com.example.calendarlist.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    init {
        Log.d("onOkClick", "SaveTaskUseCase")
    }
//    operator fun invoke(): Flow<Unit> = flow {
//        //repository.saveTask(taskDetail.toTaskEntity())
//        emit(repository.saveTask(taskDetail.toTaskEntity()))
//    }
    fun saveTask(taskDetail: TaskDetail): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            repository.saveTask(taskDetail.toTaskEntity())
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}
//        GlobalScope.launch(Dispatchers.IO) {
//            repository.saveTask(taskDetail.toTaskEntity())
//        }