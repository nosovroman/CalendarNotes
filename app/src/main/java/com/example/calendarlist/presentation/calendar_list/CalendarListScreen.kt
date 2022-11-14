package com.example.calendarlist.presentation.calendar_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calendarlist.presentation.RoomViewModel
import com.example.calendarlist.common.Screen
import com.example.calendarlist.presentation.calendar_list.components.Calendar
import com.example.calendarlist.presentation.calendar_list.components.TaskCreator
import com.example.calendarlist.presentation.calendar_list.components.TaskItem

@Composable
fun CalendarListScreen(
    navController: NavController,
    viewModel: CalendarListViewModel = hiltViewModel(),
    roomViewModel: RoomViewModel = hiltViewModel()
) {
    val listTask = roomViewModel.taskList.value

    Scaffold {
        LazyColumn {
            item {
                Calendar(
                    onDateClick = {
                        viewModel.initByChangeDay(it)
                        roomViewModel.getTasks(it)
                    }
                )
            }
            item {
                TaskCreator(
                    titleValue = viewModel.titleState,
                    onTitleChange = { viewModel.setTitle(it) },
                    descriptionValue = viewModel.descriptionState,
                    onDescriptionChange = { viewModel.setDescription(it) },
                    chosenMenuTitle = viewModel.chosenTimeState,
                    menuExpanded = viewModel.expandedMenuState,
                    onMenuClick = { viewModel.setExpandedMenu(true) },
                    menuItems = viewModel.computeFreeTime(listTask.tasks),
                    onItemClick = {
                        viewModel.setChosenTime(it)
                        viewModel.setExpandedMenu(false)
                    },
                    onDismissRequest = { viewModel.setExpandedMenu(false) },
                    onOkClick = {
                        viewModel.composeTaskDetail()?.let {
                            roomViewModel.saveTask(it)
                            viewModel.initByChangeDay(it.day)
                        }
                    }
                )
            }
            items(listTask.tasks.sortedBy { it.dateStart }) { task ->
                TaskItem(
                    task = task,
                    onItemClick = {
                        navController.navigate(route = Screen.DetailTask.createRoute(task.id))
                    }
                )
            }
        }
    }
}