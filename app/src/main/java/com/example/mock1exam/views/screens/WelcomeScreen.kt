package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.AppImageWithResource
import com.example.mock1exam.views.reusables.CircularImageButton
import com.example.mock1exam.views.reusables.VerticalSpacer

@Composable
fun WelcomeScreen(
    navController: NavController
){
    Column(
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.Start,
       modifier = Modifier
           .fillMaxSize()
           .padding(Dm.marginMedium)
    ){
        // image
        AppImageWithResource(
            resourceId = R.drawable.welcome_page_cat,
            aspectRatio = 1f
        )

        VerticalSpacer(Dm.marginLarge)

        // title
        Text(
            text = "HUMH!",
            style = MaterialTheme.typography.body1,
            fontSize = 40.sp,
            color = MaterialTheme.colors.primary
        )

        // sub-title
        Text(
            text = "HUMAN!",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.secondary
        )

        VerticalSpacer(Dm.marginLarge)

        // sub-text
        Text(
            text = "you've decided to have a cat?\n let us kelp you!",
            style = MaterialTheme.typography.body1,
        )

        VerticalSpacer(Dm.marginLarge)

        // meow icon
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            CircularImageButton(
                imageResource = R.drawable.cat_paw_icon,
                backgroundColor = Color.White
            ) {
                // go to login screen
                navController.navigate(Screen.LoginScreen.route)
            }
        }
    }
}

