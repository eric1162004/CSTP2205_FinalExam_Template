package com.example.mock1exam.views.reusables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mock1exam.ui.theme.Dm

/**
 * You cannot change input field padding with this.
 * Use CustomInputField instead
 * **/
@Composable
fun IconInputField(
    backgroundColor: Color = Color.White,
    borderWidth: Dp = Dm.borderWidth,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    iconSize: Dp = 35.dp,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    placeHolderText: String = "placeholder",
    textColor: Color = Color.Black,
    value: String,
    onValueChange: (newValue: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = borderWidth,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            ),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small),
            textStyle = TextStyle(
                fontSize = fontSize
            ),
            value = value,
            leadingIcon = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(iconSize)
                )
            },
            onValueChange = { onValueChange(it) },
            placeholder = { Text(placeHolderText) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                textColor = textColor,
                placeholderColor = Color.LightGray,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            )
        )
    }
}