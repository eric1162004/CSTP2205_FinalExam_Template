package com.example.mock1exam.views.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.auth.Auth
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.*

@Composable
fun LoginScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "LOG",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "IN",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginExtraLarge),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        LoginScreenContent(navController)
    }
}

@Composable
private fun LoginScreenContent(
    navController: NavController
) {
    val auth by remember { mutableStateOf(Auth()) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRememberMeChecked by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = Dm.marginLarge * 2)
    ) {
        // name
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placeHolderText = "email",
            placerHolderTextColor = MaterialTheme.colors.secondary,
            value = email
        ) { email = it }

        Spacer(modifier = Modifier.height(Dm.marginLarge))

        // password
        CustomTextFieldWithImageIcon(
            imageResourceId = R.drawable.cat_pink_paw_icon,
            padding = Dm.marginTiny,
            placerHolderTextColor = MaterialTheme.colors.secondary,
            placeHolderText = "password",
            visualTransformation = PasswordVisualTransformation(),
            value = password
        ) { password = it }

        Spacer(modifier = Modifier.height(Dm.marginMedium))

        // remember me
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CheckboxField(
                label = "remember me",
                labelFontStyle = MaterialTheme.typography.caption,
                checked = isRememberMeChecked
            ) {
                isRememberMeChecked = !isRememberMeChecked
            }

            ClickableLink(
                label = "Forget password",
                style = MaterialTheme.typography.caption
            ) {
                // TODO: On "Forget password?" Clicked
            }
        }

        Spacer(modifier = Modifier.height(Dm.marginLarge))
        Spacer(modifier = Modifier.height(Dm.marginMedium))

        // Show Error Message
        ErrorMessage(
            errorMessage = errorMessage,
            modifier = Modifier.align(CenterHorizontally)
        )

        // Login Button
        AppButton(
            label = "log in",
            fontColor = Color.White,
            buttonColor = MaterialTheme.colors.secondary,
        ) {
            errorMessage = ""

            auth.signInWithEmailAndPassword(
                email = email,
                password = password,
                onError = { message ->
                    errorMessage = message ?: "Sign In failed. Please try again"
                },
                onSuccess = {
                    navController.navigate(Screen.SearchItemListScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(Dm.marginMedium))

        // or sign in with
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "or sign in with",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .weight(1.25f)
                    .padding(horizontal = Dm.marginSmall)
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(Dm.marginMedium))

        // facebook, google icons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularImageButton(
                backgroundColor = Color.White,
                imageResource = R.drawable.facebook_icon
            ) {
                //TODO: Facebook Login
            }

            Spacer(modifier = Modifier.width(Dm.marginSmall))

            CircularImageButton(
                backgroundColor = Color.White,
                imageResource = R.drawable.google_icon
            ) {
                //TODO: Google Login
            }
        }

        Spacer(modifier = Modifier.height(Dm.marginMedium * 4))

        // Don't have an account
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account? ",
                style = MaterialTheme.typography.caption
            )

            ClickableLink(
                label = "Create one",
                style = MaterialTheme.typography.caption
            ) {
                navController.navigate(Screen.SignUpScreen.route)
            }
        }
    }
}