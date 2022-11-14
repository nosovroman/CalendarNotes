package com.example.calendarlist.presentation.calendar_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calendarlist.common.Constants.DEFAULT_TIME_TEXT
import com.example.calendarlist.domain.model.Task
import com.example.calendarlist.domain.model.TaskDetail

//@HiltViewModel
class CalendarListViewModel : ViewModel() {
        // CALENDAR
    var chosenDateState by mutableStateOf("")
        private set
    fun setChosenDate(newState: String) {
        chosenDateState = newState
    }


        // SAVE_TASK FORM
    var titleState by mutableStateOf("")
        private set
    fun setTitle(newState: String) {
        titleState = newState
    }

    var descriptionState by mutableStateOf("")
        private set
    fun setDescription(newState: String) {
        descriptionState = newState
    }

    fun computeFreeTime(existTasks: List<Task>): List<Int> {
        val existTimes = existTasks.map { it.dateStart }
        val freeListHours = (0..23).toList() as MutableList
        freeListHours.removeAll(existTimes)
        return freeListHours
    }

    var chosenTimeState by mutableStateOf(DEFAULT_TIME_TEXT)
        private set
    fun setChosenTime(newState: Int) {
        chosenTimeState = newState
    }

    var expandedMenuState by mutableStateOf(false)
        private set
    fun setExpandedMenu(isExpanded: Boolean) {
        expandedMenuState = isExpanded
    }

    fun composeTaskDetail(): TaskDetail? {
        Log.d("onOkClick", "composeTaskDetail")
        Log.d("onOkClick", "$chosenDateState $titleState $descriptionState $chosenTimeState")
        return if (chosenDateState != "" && titleState.trim() != "" &&
            descriptionState.trim() != "" && chosenTimeState != DEFAULT_TIME_TEXT)
                TaskDetail(
                    dateStart = chosenTimeState,
                    name = titleState.trim(),
                    description = descriptionState.trim(),
                    day = chosenDateState
                )
        else null
    }

    fun initByChangeDay(day: String) {
        setChosenDate(day)
        setTitle("")
        setDescription("")
        setChosenTime(DEFAULT_TIME_TEXT)
    }
}