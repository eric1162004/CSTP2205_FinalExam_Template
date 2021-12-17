package com.example.mock1exam.views.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun AppDropdownMenu(
    menuLabel: String,
    fontColor: Color = MaterialTheme.colors.primary,
    fontStyle: TextStyle = MaterialTheme.typography.body1,
    dropDownItems: List<String>,
    modifier: Modifier = Modifier,
    onSelected: (selectedIndex: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = dropDownItems
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .clickable(onClick = { expanded = true })
        ) {
            Text(
                text = menuLabel,
                style = fontStyle,
                color = fontColor
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = fontColor
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            items.forEachIndexed { index, itemText ->
                DropdownMenuItem(onClick = {
                    expanded = false

                    selectedIndex = index
                    onSelected(dropDownItems[selectedIndex])
                }) {
                    Text(
                        text = itemText,
                        style = fontStyle,
                        color = fontColor
                    )
                }
            }
        }
    }
}