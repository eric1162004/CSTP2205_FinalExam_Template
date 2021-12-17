package com.example.mock1exam.views.reusables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun VerticalSpacer(
    height: Dp = Dm.marginMedium,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(height))
}