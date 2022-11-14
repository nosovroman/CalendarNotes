package com.example.calendarlist.presentation.calendar_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendarlist.common.Constants
import com.example.calendarlist.domain.extensions.toTimeFormat

@Composable
fun TaskCreator(
    titleValue: String,
    onTitleChange: (String) -> Unit,
    descriptionValue: String,
    onDescriptionChange: (String) -> Unit,
    chosenMenuTitle: Int,
    menuExpanded: Boolean,
    onMenuClick: () -> Unit,
    menuItems: List<Int>,
    onItemClick: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    onOkClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        TextFieldComponent(
            modifier = Modifier.fillMaxWidth(),
            currentValue = titleValue,
            onValueChange = { value -> onTitleChange(value) },
            placeholder =  "Название события"
        )
        Spacer(modifier = Modifier.size(5.dp))
        TextFieldComponent(
            modifier = Modifier.fillMaxWidth(),
            currentValue = descriptionValue,
            onValueChange = { value -> onDescriptionChange(value) },
            placeholder =  "Описание"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            DropdownMenuComponent(
                chosenItemTitle = if (chosenMenuTitle == Constants.DEFAULT_TIME_TEXT) "Время"
                                  else chosenMenuTitle.toTimeFormat(),
                expanded = menuExpanded,
                onMenuClick = onMenuClick,
                items = menuItems,//.map { "$it:00-${it+1}:00" },
                onItemClick = onItemClick,
                onDismissRequest = onDismissRequest
            )
            Spacer(modifier = Modifier.size(5.dp))
            Button(onClick = { onOkClick() }) {
                Text(text = "Создать")
            }
        }


    }
}