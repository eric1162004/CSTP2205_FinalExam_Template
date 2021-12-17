package com.example.mock1exam.views.reusables

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun ClickableLink(
    label: String,
    color: Color = MaterialTheme.colors.primary,
    style: TextStyle = MaterialTheme.typography.body1,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = modifier.clickable { onClick() },
        style = style,
        color = color,
    )
}