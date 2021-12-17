package com.example.mock1exam.views.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mock1exam.utils.auth.Auth
import com.example.mock1exam.views.navigation.Screen

@Composable
fun Drawer(navController: NavController) {
    val auth by remember { mutableStateOf(Auth()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .shadow(elevation = 1.dp)
    ) {
        AppButton(
            label = "logout",
            fontColor = Color.White
        ) {
            auth.signOut {
                navController.navigate(Screen.LoginScreen.route)
            }
        }
    }
}
