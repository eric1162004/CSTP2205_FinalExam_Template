package com.example.mock1exam.views.reusables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun HorizontalDivider(
    color: Color = MaterialTheme.colors.primary,
    height: Dp = Dm.borderWidth,
    modifier: Modifier
) {
    Divider(
        color = color,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    )
}