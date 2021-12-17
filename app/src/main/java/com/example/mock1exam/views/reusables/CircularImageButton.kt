package com.example.mock1exam.views.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularImageButton(
    imageResource: Int,
    buttonSize: Dp = 50.dp,
    backgroundColor: Color = MaterialTheme.colors.primary,
    imagePadding: Dp = 0.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "",
        modifier = modifier
            .size(buttonSize)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(imagePadding)
            .clickable {
                onClick()
            }
    )
}