package com.example.mock1exam.views.reusables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorMessage(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    if (errorMessage.isNotEmpty()) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = modifier.fillMaxWidth()
        )
    }
}