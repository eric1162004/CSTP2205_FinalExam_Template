package com.example.mock1exam

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.assignment4.utils.map.Coordinate
import com.example.mock1exam.backend.repositories.BreedRepository
import com.example.mock1exam.backend.repositories.CatRepository
import com.example.mock1exam.ui.theme.Mock1ExamTheme
import com.example.mock1exam.utils.map.AppMapView
import com.example.mock1exam.views.screens.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class MainActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Temp Fix to hide System Status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContent {
            Mock1ExamTheme {
                MainScreen()
//                TestScreen()
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun TestScreen() {
    val breedRepository by remember { mutableStateOf(BreedRepository()) }
//    breedRepository.getAll()
}



