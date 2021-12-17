package com.example.mock1exam.views.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mock1exam.R

@Composable
fun AppImageWithUrl(
    url: String,
    width: Dp = 200.dp,
    aspectRatio: Float = 1f,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            }
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(width)
            .aspectRatio(aspectRatio)
    )
}

