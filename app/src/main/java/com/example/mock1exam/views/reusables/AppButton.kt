package com.example.mock1exam.views.reusables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun AppButton(
    buttonColor: Color = MaterialTheme.colors.primary,
    buttonCorner: Shape = MaterialTheme.shapes.medium,
    buttonWidth: Dp = 120.dp,
    fontStyle: TextStyle = MaterialTheme.typography.body1,
    fontColor: Color = Color.Black,
    label: String,
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.width(buttonWidth),
        onClick = { onClick() },
        shape = buttonCorner,
        contentPadding = PaddingValues(padding),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = label,
            style = fontStyle,
            color = fontColor
        )
    }
}