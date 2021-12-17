package com.example.mock1exam.views.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodvillage2205.view.composables.CameraCapture
import com.example.mock1exam.R
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.gallery.GallerySelect
import com.example.mock1exam.views.app_reusables.JumboIconButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.foodvillage2205.model.repositories.StorageRepository
import com.example.mock1exam.backend.models.Cat
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.backend.repositories.ShopItemRepository
import com.example.mock1exam.utils.validation.isValidFloat
import com.example.mock1exam.utils.validation.isValidInteger
import com.example.mock1exam.views.reusables.*

@ExperimentalPermissionsApi
@Composable
fun UploadScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "UPLOAD ",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Text(
                            text = "ITEM",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                contentPadding = PaddingValues(
                    vertical = Dm.marginLarge,
                    horizontal = Dm.marginMedium
                ),
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        },
        backgroundColor = Color.White
    ) {
        UploadScreenContent(navController)
    }
}

@ExperimentalPermissionsApi
@Composable
fun UploadScreenContent(
    navController: NavController
) {
    var showCamera by remember { mutableStateOf(false) }
    var imageFile by remember { mutableStateOf<File?>(null) }

    var showGallery by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val scope = rememberCoroutineScope()
//    val catRepository by remember { mutableStateOf(CatRepository()) }
    val shopItemRepository by remember { mutableStateOf(ShopItemRepository()) }
    val storageRepository by remember { mutableStateOf(StorageRepository()) }

    // Fields
//    var name by remember { mutableStateOf("") }
//    var breed by remember { mutableStateOf("") }
//    var age by remember { mutableStateOf<Int?>(null) }
//    var weight by remember { mutableStateOf<Float?>(null) }
//    var gender by remember { mutableStateOf("") }
//    var fact by remember { mutableStateOf("")

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf<Float?>(null) }
    var count by remember { mutableStateOf<Int?>(null) }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    if (!showCamera && !showGallery) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dm.marginLarge)

        ) {
            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    item {
                        // Select Camera
//                        if (imageFile != null) {
//                            AppImageWithUrl(url = imageFile!!.toUri().toString())
//                        } else {
//                            JumboIconButton(iconResourceId = R.drawable.camera) {
//                                // camera button pressed
//                                showCamera = true
//                            }
//                        }

                        // Select Image
                        if (imageUri != null) {
                            AppImageWithUrl(
                                url = imageUri?.toString() ?: "",
                                modifier = Modifier.clickable {
                                    imageUri == null
                                }
                            )
                        }
                        // Select Gallery
                        JumboIconButton(
                            iconResourceId = R.drawable.add
                        ) {
                            // gallery button pressed
                            showGallery = true
                        }
                    }
                }

                VerticalSpacer(Dm.marginMedium)

                // name
                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "name",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    value = name
                ) { name = it }

                VerticalSpacer(Dm.marginMedium)

                // count (is an integer)
                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "Inventory Count",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = count?.toString() ?: "",
                ) {
                    if (it.isEmpty()) {
                        count = null
                    } else if (isValidInteger(it)) {
                        count = it?.toInt()
                    }
                }

                VerticalSpacer(Dm.marginMedium)

                // price (is a float)
                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "price",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = price?.toString() ?: "",
                ) {
                    if (it.isEmpty()) {
                        price = 0f
                    } else if (isValidFloat(it)) {
                        price = it?.toFloat()
                    }
                }

                VerticalSpacer(Dm.marginMedium)

                // gender
//                SelectionField(
//                    label = "Gender",
//                    dropDownItems = listOf("Boy", "Girl", "Other"),
//                ) {
//                    gender = it
//                }

                VerticalSpacer(Dm.marginMedium)

                // breed
//                SelectionField(
//                    label = "breed",
//                    dropDownItems = listOf("Persian", "Maine Coon", "Shorthair", "Siamese", "Other")
//                ) {
//                    // on breed selected
//                    breed = it
//                }

                // category
                SelectionField(
                    label = "categories",
                    dropDownItems = listOf("A", "B", "C", "D", "Other")
                ) {
                    // on breed selected
                    category = it
                }

                VerticalSpacer(Dm.marginMedium)

                // fact (long text)
                CustomTextFieldWithImageIcon(
                    padding = Dm.marginTiny,
                    placeHolderText = "Description",
                    placerHolderTextColor = MaterialTheme.colors.secondary,
                    value = description,
                    maxLines = 4,
                    modifier = Modifier.height(Dm.marginExtraLarge * 2)
                ) { description = it }

                VerticalSpacer(Dm.marginLarge)

                // Error Message
                ErrorMessage(errorMessage = errorMessage)

                // submit button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    AppButton(
                        label = "submit",
                        fontColor = Color.White,
                        buttonColor = MaterialTheme.colors.secondary,
                        modifier = Modifier.width(Dm.buttonWidthDefault)
                    ) {
                        errorMessage = ""

                        // prepare ite to be send
//                        var cat = Cat(
//                            name = name,
//                            age = age ?: 0,
//                            weight = weight ?: 0f,
//                            breeds = listOf(breed),
//                            sex = gender,
//                            description = fact,
//                        )

                        var shopItem = ShopItem(
                            name = name,
                            price = price ?: 0f,
                            count = count ?: 0,
                            description = description,
                            categories = listOf(category)
                        )

                        // validate cat
//                        errorMessage = validateCat(cat)

                        // validate shopItem
                        errorMessage = validateShopItem(shopItem)
                        // do not send cat if it is not invalid
                        if (errorMessage.isNotEmpty()) return@AppButton

                        // On Submit button pressed
                        scope.launch(Dispatchers.IO) {
                            // upload image
                            if (imageUri != null) {
                                storageRepository.uploadImage(imageUri!!) { url, fileName ->
                                    // set item image and fileName
//                                    cat.imageURLs.add(url.toString())
                                    shopItem.imageURL = url.toString()

                                    // save item
//                                    catRepository.create(cat) {
//                                        // return to original screen
//                                        navController.popBackStack()
//                                    }
                                    shopItemRepository.create(shopItem) {
                                        navController.popBackStack()
                                    }
                                }
                            } else {
                                // save item without image provided
                                shopItemRepository.create(shopItem) {
                                    // return to original screen
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }
        }
    } else if (showCamera && !showGallery) {
        CameraCapture() {
            showCamera = false
            imageUri = null
            imageFile = it
        }
    } else {
        GallerySelect() {
            showGallery = false
            imageFile = null
            if (it != null) {
                imageUri = it
            }
        }
    }
}

fun validateShopItem(shopItem: ShopItem): String {
    var errorMessages = ""

    if (shopItem.name.isEmpty()) {
        errorMessages += "Item must have a name.\n"
    }

    if (shopItem.price < 0) {
        errorMessages += "Price must be greater or equal to 0.\n"
    }

    if (shopItem.count < 0) {
        errorMessages += "Price must be greater or equal to 0.\n"
    }

    return errorMessages
}

private fun validateCat(cat: Cat): String {
    var errorMessages = ""

    if (cat.name.isEmpty()) {
        errorMessages += "Cat must have a name.\n"
    }

    if (cat.age < 0) {
        errorMessages += "Cat must be greater or equal to 0.\n"
    }

    return errorMessages;
}

