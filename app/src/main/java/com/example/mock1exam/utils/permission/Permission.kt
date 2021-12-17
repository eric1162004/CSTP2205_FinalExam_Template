/**
 * @ Author: 2205 Team (Food Village)
 * @ Create Time: 2021-11-11 20:41:02
 * @ Description: This file contains functions for obtain permissions from app users
 */

package com.example.mock1exam.utils.permission

import android.Manifest
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

/**
 * A generic function for obtaining permissions from users.
 * @param [permission] [permission type]
 * @param [rationale] [reason for asking the permission]
 * @param [permissionNotAvailableContent] [composable to display when permission is not obtained]
 * @param [content] [composable to display when permission is obtained]
 */
@ExperimentalPermissionsApi
@Composable
fun Permission(
    permission: String = Manifest.permission.CAMERA,
    rationale: String = "This permission is important for this app. Please grant the permission.",
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(permission)

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            Rationale(
                text = rationale,
                onRequestPermission = { permissionState.launchPermissionRequest() }
            )
        },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = content
    )
}

/**
 * A Alert Dialog to display when asking for permissions.
 * @param [text] [text message]
 * @param [onRequestPermission] [callback when requests have been granted]
 */
@Composable
private fun Rationale(
    text: String,
    onRequestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /* Don't */ },
        title = {
            Text(
                text = "Permission request",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        text = {
            Text(
                text,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
            )
        },
        confirmButton = {
            TextButton(
                modifier = Modifier.fillMaxWidth(0.3f),
                onClick = onRequestPermission
                ) {
                Text("OK",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500
                )
            }
        },
        contentColor = Color.Black
    )
}