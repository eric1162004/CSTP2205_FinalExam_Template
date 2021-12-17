package com.example.mock1exam.views.app_reusables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun JumboIconButton(
    iconResourceId: Int,
    iconTint: Color = MaterialTheme.colors.secondary,
    iconSize: Dp = 80.dp,
    buttonWidth: Dp = 150.dp,
    buttonBorderWidth: Dp = Dm.borderWidth,
    buttonBorderColor: Color = MaterialTheme.colors.primary,
    buttonCornerRadius: Shape = MaterialTheme.shapes.medium,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(buttonWidth)
            .aspectRatio(1.5f)
            .border(
                width = buttonBorderWidth,
                color = buttonBorderColor,
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onClick() },
    ) {
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .size(iconSize)
                .aspectRatio(1.5f)
        )
    }
}