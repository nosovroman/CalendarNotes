package com.example.calendarlist.presentation.calendar_list.components

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Calendar(onDateClick: (String) -> Unit) {
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.fillMaxWidth(),
        update = {
            it.setOnDateChangeListener { _, y, m, d ->
                Log.d("Calendar", "$y $m $d")
                onDateClick("$y $m $d")
            }
        }
    )
}