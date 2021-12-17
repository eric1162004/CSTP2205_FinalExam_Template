package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment4.utils.map.Coordinate
import com.example.assignment4.view.reusables.ImageRow
import com.example.mock1exam.R
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.Cat
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.backend.repositories.CatRepository
import com.example.mock1exam.backend.repositories.ShopItemRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.map.AppMapView
import com.example.mock1exam.views.reusables.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun DetailsScreen(
    navController: NavController,
    id: String
) {
    Scaffold(
        backgroundColor = Color.White
    ) {
        DetailsScreenContent(id)
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
private fun DetailsScreenContent(id: String) {
//    val catRepository by remember { mutableStateOf(CatRepository()) }
    val shopItemRepository by remember { mutableStateOf(ShopItemRepository()) }
    var errorMessage by remember { mutableStateOf("") }

    // Get a single cat detail by id
//    val cat = produceState(initialValue = Cat()) {
//        catRepository.getById(id) {
//            if (it is Resource.Success<*>) {
//                value = it.data as Cat
//            } else {
//                errorMessage = "Something went wrong. Please try again."
//            }
//        }
//    }.value

    val shopItem = produceState(initialValue = ShopItem()) {
        shopItemRepository.getById(id) {
            if (it is Resource.Success<*>) {
                value = it.data as ShopItem
            } else {
                errorMessage = "Something went wrong. Please try again."
            }
        }
    }.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorMessage(errorMessage = errorMessage)

        // check if an Item is return from firebase
        if (shopItem.id.isNotEmpty()) {
            // image layer (base layer)
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                AppImageWithUrl(
                    url = shopItem.imageURL ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // item info layer (surface layer)
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                VerticalSpacer(modifier = Modifier.weight(1f))

                Card(
                    elevation = 0.dp,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.weight(2f)
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dm.marginMedium)
                    ) {
                        item {
                            // item name
                            Text(
                                text = shopItem.name,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.primary,
                                fontSize = 40.sp,
                            )

                            // row of icons
//                            IconsRow()

                            // all cat images
//                            if (shopItem.imageURLs.size > 0) {
//                                VerticalSpacer(Dm.marginMedium)
//                                ImageRow(
//                                    imageUrls = shopItem.imageURLs,
//                                    modifier = Modifier.fillMaxWidth()
//                                )
//                            }
                            
                            AppImageWithUrl(url = shopItem.imageURL)

                            VerticalSpacer(Dm.marginMedium)

                            // cat description
                            Text(
                                text = shopItem.description,
                                style = MaterialTheme.typography.body1
                            )

                            VerticalSpacer(Dm.marginMedium)

                            // cat coordinate and location map
//                            val coordinate = Coordinate(
//                                latitude = cat.location.lat,
//                                longitude = cat.location.lng
//                            )
//
//                            AppMapView(
//                                coordinates = listOf(coordinate),
//                                flyToLocation = coordinate,
//                                modifier = Modifier
//                                    .width(400.dp)
//                                    .height(350.dp)
//                            )

                            VerticalSpacer(Dm.marginExtraLarge)

                            // adopt button
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.End,
//                                modifier = Modifier.fillMaxWidth(),
//                            ) {
//                                AppButton(
//                                    label = "buy",
//                                    buttonColor = MaterialTheme.colors.secondary,
//                                    fontColor = Color.White,
//                                    modifier = Modifier.width(Dm.buttonWidthDefault)
//                                ) {
//                                    // on adopt pressed
//                                }
//                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun IconsRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        CircularImageButton(
            imageResource = R.drawable.cat_face_icon,
            backgroundColor = Color.White
        ) {
            // cat face button
        }

        CircularImageButton(
            imageResource = R.drawable.cat_sleep_icon,
            backgroundColor = Color.White
        ) {
            // cat sleep button
        }

        CircularImageButton(
            imageResource = R.drawable.cat_heart_icon,
            backgroundColor = Color.White
        ) {
            // cat heart button
        }
    }
}