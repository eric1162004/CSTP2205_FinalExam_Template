package com.example.assignment4.view.reusables

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mock1exam.R
import kotlin.math.ceil

@Composable
fun ImageGrid(
    imageUrls: List<String>,
    columnSize: Int = 4,
    imageWidth: Dp = 78.dp,
    modifier: Modifier = Modifier
) {
    var imageCount by remember { mutableStateOf(imageUrls.size) }
    var rowCount by remember { mutableStateOf(ceil(imageCount.toFloat().div(columnSize)).toInt()) }
    var currentIndex by remember { mutableStateOf(0) }

    Column(modifier) {
        repeat(rowCount) {
            Row {
                repeat(columnSize) {
                    if (currentIndex < imageCount) {
                        Image(
                            painter = rememberImagePainter(
                                data = imageUrls[currentIndex++],
                                builder = {
                                    placeholder(R.drawable.image_placeholder)
                                    error(R.drawable.image_placeholder)
                                }),
                            contentDescription = null,
                            modifier = Modifier
                                .width(imageWidth)
                                .aspectRatio(1f)
                                .padding(0.5.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        )
                    }
                }
            }
        }
    }
}