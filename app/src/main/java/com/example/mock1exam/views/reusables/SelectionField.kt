package com.example.mock1exam.views.reusables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mock1exam.ui.theme.Dm

@Composable
fun SelectionField(
    label: String,
    dropDownItems: List<String>,
    modifier: Modifier = Modifier,
    onSelected: (selectedItem: String) -> Unit
) {
    var menuLabel by remember { mutableStateOf("choose one") }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )

        HorizontalSpacer(Dm.marginTiny)

        AppDropdownMenu(
            menuLabel = menuLabel,
            dropDownItems = dropDownItems,
            modifier = Modifier
                .border(
                    width = Dm.borderWidth,
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = Dm.marginSmall, vertical = Dm.marginTiny),
            onSelected = {
                menuLabel = it
                onSelected(it)
            }
        )
    }
}