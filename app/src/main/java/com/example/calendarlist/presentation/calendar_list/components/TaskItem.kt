package com.example.calendarlist.presentation.calendar_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendarlist.domain.extensions.toTimeFormat
import com.example.calendarlist.domain.model.Task

@Composable
fun TaskItem(
    task: Task,
    onItemClick: (Task) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(task) }
            .padding(20.dp)
    ) {
        Text(text = task.dateStart.toTimeFormat())
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = task.name)
    }
}