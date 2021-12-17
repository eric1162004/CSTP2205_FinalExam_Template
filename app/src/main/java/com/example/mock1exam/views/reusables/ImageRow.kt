package com.example.assignment4.view.reusables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mock1exam.R

@Composable
fun ImageRow(
    imageUrls: List<String> = listOf(),
    aspectRatio: Float = 1.8f,
    imageWidth: Dp = 200.dp,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(imageUrls) { item ->
            Image(
                painter = rememberImagePainter(
                    data = item,
                    builder = {
                        placeholder(R.drawable.image_placeholder)
                        error(R.drawable.image_placeholder)
                    }),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .width(imageWidth)
                    .aspectRatio(aspectRatio),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
        }
    }
}