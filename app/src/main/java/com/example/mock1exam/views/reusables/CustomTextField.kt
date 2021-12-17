package com.example.mock1exam.views.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mock1exam.ui.theme.Dm

@Composable
fun CustomTextField(
    backgroundColor: Color = Color.White,
    borderWidth: Dp = Dm.borderWidth,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    fontColor: Color = Color.Black,
    iconSize: Dp = 35.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imageVector: ImageVector? = null,
    imageVectorTint: Color = Color.LightGray,
    textFieldBorderRadius: Shape = MaterialTheme.shapes.small,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
    padding: Dp = Dm.marginSmall,
    placeHolderText: String = "placeholder",
    placerHolderTextColor: Color = Color.LightGray,
    value: String,
    onValueChange: (text: String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .border(
                width = borderWidth,
                color = MaterialTheme.colors.primary,
                shape = textFieldBorderRadius
            )
            .padding(padding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // leading icon
        imageVector?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = imageVectorTint,
                modifier = Modifier
                    .size(iconSize)
                    .weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .weight(.35f)
            )
        }

        // text field
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = fontColor,
                fontSize = fontSize
            ),
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeHolderText,
                            color = placerHolderTextColor,
                            fontSize = fontSize
                        )
                    }
                }
                innerTextField()
            }
        )
    }
}

