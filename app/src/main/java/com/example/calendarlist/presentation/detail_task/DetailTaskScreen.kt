package com.example.calendarlist.presentation.detail_task

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calendarlist.domain.extensions.toTimeFormat
import com.example.calendarlist.presentation.RoomViewModel

@Composable
fun DetailTaskScreen(
    contentId: Int,
    roomViewModel: RoomViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        roomViewModel.getTaskById(contentId)
    }
    val currentTask = roomViewModel.curTask.value

    Scaffold {
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (currentTask.task != null) {
                item { Text("${currentTask.task.day}, ${currentTask.task.dateStart.toTimeFormat()}")
                    Spacer(modifier = Modifier.size(5.dp))}

                item { Text(currentTask.task.name)
                    Spacer(modifier = Modifier.size(5.dp))}

                item {
                    Divider()
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(currentTask.task.description) }
            }
        }
    }
}