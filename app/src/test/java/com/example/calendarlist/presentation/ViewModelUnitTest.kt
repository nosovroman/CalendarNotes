package com.example.calendarlist.presentation

import android.util.Log
import com.example.calendarlist.domain.model.Task
import com.example.calendarlist.presentation.calendar_list.CalendarListViewModel
import org.junit.Test

import org.junit.Assert.*

class ViewModelUnitTest {
    private val detailViewModel = CalendarListViewModel()

    @Test
    fun computeFreeTime() {
        val testList1 = listOf(Task(0, 0, "task1"),
                               Task(1, 5, "task2"),
                               Task(2, 10, "task3"))
        val requiredAnswer = (1..4).toList() + (6..9).toList() + (11..23).toList()

        val freeList = detailViewModel.computeFreeTime(testList1)
        assertTrue("$freeList = $requiredAnswer", freeList == requiredAnswer)
    }
}