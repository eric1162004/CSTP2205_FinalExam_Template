package com.example.mock1exam.views.reusables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun CheckboxField(
    checked: Boolean,
    label: String,
    labelTextColor: Color = MaterialTheme.colors.primary,
    labelFontStyle: TextStyle = MaterialTheme.typography.body1,
    spacer: Dp = Dm.marginSmall,
    modifier: Modifier = Modifier,
    onCheckedChange: (checked: Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier.border(
                width = Dm.borderWidth,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            )
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Black,
                )
            )
        }

        Spacer(modifier = Modifier.width(spacer))

        Text(
            text = label,
            style = labelFontStyle,
            color = labelTextColor
        )
    }
}