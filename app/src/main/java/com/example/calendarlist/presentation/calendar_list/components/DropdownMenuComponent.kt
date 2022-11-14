package com.example.calendarlist.presentation.calendar_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendarlist.domain.extensions.toTimeFormat

@Composable
fun DropdownMenuComponent(
    chosenItemTitle: String = "",
    expanded: Boolean,
    onMenuClick: () -> Unit,
    items: List<Int>,
    onItemClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    Box(modifier = Modifier.width(100.dp)) {
        TextRectangleComponent(
            text = chosenItemTitle,
            modifier = Modifier.clickable {
                onMenuClick()
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            content = {
                items.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onItemClick(it)
                        },
                        content = { Text(it.toTimeFormat()) }
                    )
                }
            }
        )
    }
}