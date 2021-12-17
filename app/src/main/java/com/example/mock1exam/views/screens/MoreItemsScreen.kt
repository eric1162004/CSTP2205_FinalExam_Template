package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.backend.repositories.ShopItemRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.views.app_reusables.ShopItemCard
import com.example.mock1exam.views.navigation.Screen

@ExperimentalFoundationApi
@Composable
fun MoreItemsScreen(
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
                            text = "MORE",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "ITEMS",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(vertical = Dm.marginLarge),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        MoreItemsScreenContent(navController)
    }
}

@ExperimentalFoundationApi
@Composable
private fun MoreItemsScreenContent(
    navController: NavController
) {
//    val catRepository by remember { mutableStateOf(CatRepository()) }
//    var cats by remember { mutableStateOf(listOf<Cat>()) }
    val shopItemRepository by remember { mutableStateOf(ShopItemRepository()) }
    var shopItems by remember { mutableStateOf(listOf<ShopItem>()) }

    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        shopItemRepository.getAll {
            if(it is Resource.Success){
                shopItems = it.data as List<ShopItem>
            }else{
                errorMessage = "Something went wrong."
            }
        }
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ){
        items(shopItems.size){ index ->
            ShopItemCard(
                shopItem = shopItems[index],
                onIncrement = {},
                onDecrement = {},
                modifier = Modifier.padding(horizontal = Dm.marginSmall)
            ){
                // on card pressed
                navController.navigate(
                    Screen.DetailsScreen.createRoute(shopItems[index].id))
            }
        }
    }
}
