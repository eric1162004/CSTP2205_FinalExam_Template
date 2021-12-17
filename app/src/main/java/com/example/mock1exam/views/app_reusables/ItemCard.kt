package com.example.mock1exam.views.app_reusables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mock1exam.backend.models.Cat
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.reusables.AppImageWithUrl
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun ItemCard(
    cat: Cat,
    imageWidth: Dp = 200.dp,
    imageRatio: Float = 1f,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().clickable { onClick() }
    ) {
        // cat image
        AppImageWithUrl(
            url = cat.imageURLs.getOrNull(0)?:"",
            aspectRatio = imageRatio,
            width = imageWidth,
            modifier = Modifier.clip(shape = MaterialTheme.shapes.large)
        )

        VerticalSpacer(Dm.marginSmall)

        // cat name
        Text(
            text = if (cat.name.isNotEmpty()) cat.name else "unavailble",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        // cat breed
        Text(
            text = cat.breeds[0],
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )

        // cat age
        Text(
            text = "${cat.age} years old",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )

        // cat sex
        Text(
            text = cat.sex,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )
    }
}