package com.example.mock1exam.views.reusables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
private fun ToggleSwitch(
    modifier: Modifier = Modifier,
    switchState: Boolean,
    onSwitchToggle: () -> Unit,
) {
    Switch(
        checked = switchState,
        onCheckedChange = { onSwitchToggle() },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.secondary,
//            uncheckedThumbColor = Color.White,
//            uncheckedTrackColor = Color.Black,
            uncheckedTrackAlpha = 1f
        ),
        modifier = modifier
    )
}