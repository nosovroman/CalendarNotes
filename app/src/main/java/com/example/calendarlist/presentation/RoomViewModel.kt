package com.example.calendarlist.presentation


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.calendarlist.common.Resource
import com.example.calendarlist.domain.model.TaskDetail
import com.example.calendarlist.domain.use_case.get_task.GetTaskUseCase
import com.example.calendarlist.domain.use_case.get_tasks.GetDayTasksUseCase
import com.example.calendarlist.domain.use_case.save_task.SaveTaskUseCase
import com.example.calendarlist.domain.model.TaskListState
import com.example.calendarlist.domain.model.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor (
    private val getDayTasksUseCase: GetDayTasksUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {
    private val _taskList = mutableStateOf(TaskListState())
    val taskList: State<TaskListState> = _taskList

    private val _curTask = mutableStateOf(TaskState())
    val curTask: State<TaskState> = _curTask

    init {
        getTasks("2022 8 29")
    }

    fun getTasks(day: String) {
        Log.d("UseCase", "getTasks: $day: ${taskList.value}")
        //GetDayTasksUseCase(repositoryRoom)
        getDayTasksUseCase.getTasks(day).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _taskList.value = TaskListState(tasks = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _taskList.value = TaskListState(error = "Err ${result.data}")
                }
                is Resource.Loading -> {
                    _taskList.value = TaskListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveTask(taskDetail: TaskDetail) {
        Log.d("UseCase", "saveTask: $taskDetail")
        Log.d("onOkClick", "taskList: ${taskList.value}")
        if (taskList.value.tasks.all { it.dateStart != taskDetail.dateStart }) {
            Log.d("onOkClick", "saveTask: $taskDetail")
            saveTaskUseCase.saveTask(taskDetail).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        //_taskList.value = TaskListState(tasks = result.data ?: emptyList())
                        getTasks(taskDetail.day)
                    }
                    is Resource.Error -> {
                        //_taskList.value = TaskListState(error = "Err ${result.data}")
                    }
                    is Resource.Loading -> {
                        //_taskList.value = TaskListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        } else Log.d("onOkClick", "saveTask: task already exists ${taskDetail.dateStart}")
    }

    fun getTaskById(id: Int) {
        Log.d("UseCase", "getTaskById: $id")
        getTaskUseCase.getTaskById(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _curTask.value = TaskState(task = result.data)
                }
                is Resource.Error -> {
                    _curTask.value = TaskState(error = "Err ${result.data}")
                }
                is Resource.Loading -> {
                    _curTask.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}