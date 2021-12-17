package com.example.mock1exam.views.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIconButton(
    backgroundColor: Color = MaterialTheme.colors.primary,
    buttonSize: Dp = 50.dp,
    imageVector: ImageVector,
    iconTint: Color = Color.Black,
    iconPadding: Dp = 5.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    IconButton(
        onClick = { onClick() },
        modifier = modifier.size(buttonSize)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .size(buttonSize)
                .clip(CircleShape)
                .background(backgroundColor)
                .padding(iconPadding)
        )
    }
}