package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodvillage2205.model.entities.User
import com.example.foodvillage2205.model.repositories.UserRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.CartItem
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.backend.repositories.ShopItemRepository
import com.example.mock1exam.utils.auth.Auth
import com.example.mock1exam.views.app_reusables.ShopItemCard
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.*
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@Composable
fun SearchItemListScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { Drawer(navController = navController) },
        topBar = {
            TopAppBar(
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dm.marginMedium),
                    ) {
                        Text(
                            text = "SEARCH",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )

                        HorizontalSpacer(Dm.marginSmall)

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
        floatingActionButton = {
            CircularIconButton(
                imageVector = Icons.Filled.Add,
                iconTint = Color.White,
            ) {
                navController.navigate(Screen.UploadScreen.route)
            }
        },
        backgroundColor = Color.White
    ) {
        SearchItemListScreenContent(navController)
    }
}

@ExperimentalFoundationApi
@Composable
private fun SearchItemListScreenContent(
    navController: NavController
) {
    val shopItemRepository by remember { mutableStateOf(ShopItemRepository()) }
    var shopItems by remember { mutableStateOf(listOf<ShopItem>()) }
    var filteredShopItems by remember { mutableStateOf(listOf<ShopItem>()) }

//    val breedRepository by remember { mutableStateOf(BreedRepository()) }
//    var breeds by remember { mutableStateOf(listOf<String>()) }

    var auth by remember { mutableStateOf(Auth()) }
    var userRespository by remember { mutableStateOf(UserRepository()) }
    var currentUser by remember { mutableStateOf(User()) }
    var shoppingCart by remember { mutableStateOf(mutableListOf<CartItem>()) }

    var searchText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Get initial data from firebase
    LaunchedEffect(key1 = true) {
        // temp fix: delay 2 secs to get the updated values
        delay(2000L)

        // get user info
        userRespository.getUserById(auth.currentUser!!.uid) {
            if (it is Resource.Success<*>) {
                currentUser = it.data as User
                shoppingCart = currentUser.cartItems
            }
        }

//        breedRepository.getAll {
//            // need to map Breed into breedName
//            var breedNames = mutableListOf<String>()
//
//            for (breed in it.data as List<Breed>) {
//                breedNames.add(breed.breed)
//            }
//
//            breeds = breedNames
//        }

        shopItemRepository.getAll {
            if (it is Resource.Success<*>) {
                shopItems = it.data as List<ShopItem>
                filteredShopItems = shopItems
            } else {
                errorMessage = "Something went wrong."
            }
        }
    }

    // *** Here are Logic for Shopper Cart ***

    // check if the cart has a particular shop item
    val getCartItem: (shopItem: ShopItem) -> CartItem? = { shopItem ->
        var filteredCart = shoppingCart.filter { cartItem ->
            cartItem.shopItem.name == shopItem.name
        }

        if (filteredCart.size > 0) {
            filteredCart[0]
        } else {
            null
        }
    }

    // Get the quantity of the cartItem
    val getCartItemCount: (shopItem: ShopItem) -> Int = { shopItem ->
        var cartItem = getCartItem(shopItem)

        if (cartItem != null) {
            cartItem.wantedQuantity
        } else {
            0
        }
    }

    // Increment or decrement the cartItem
    val changeCartItemQuantity: (shopItem: ShopItem, quantity: Int) -> Unit =
        { shopItem, quantityDelta ->
            var cartItem = getCartItem(shopItem)

            // check if the shopper cart has this shop item
            if (cartItem != null) {
                var newQuantity = cartItem.wantedQuantity + quantityDelta
                shoppingCart.remove(cartItem)

                // replace the shopper cart item with updated quantity
                // add new cart item if quantity is greater 0
                // and is less then the shop item inventory count
                if (newQuantity > 0 && shopItem.count >= newQuantity) {
                    cartItem.wantedQuantity = newQuantity
                    shoppingCart.add(cartItem)
                }
            } else {
                // only add new cart item if it is an increment
                if (quantityDelta > 0) {
                    shoppingCart.add(
                        CartItem(
                            shopItem = shopItem,
                            wantedQuantity = 1
                        )
                    )
                }
            }
        }

    // Save shopping cart before leaving the screen
    DisposableEffect(true) {
        onDispose {
            currentUser.cartItems = shoppingCart
            userRespository.updateUser(
                user = currentUser
            ) {}
        }
    }

    // *** Screen Content ***
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dm.marginMedium)
    ) {
        // search bar
        CustomTextField(
            imageVector = Icons.Filled.Search,
            imageVectorTint = MaterialTheme.colors.secondary,
            textFieldBorderRadius = MaterialTheme.shapes.medium,
            padding = Dm.marginTiny,
            placeHolderText = "search",
            placerHolderTextColor = MaterialTheme.colors.secondary,
            value = searchText
        ) {
            searchText = it

            if (searchText.isNotEmpty()) {
                filteredShopItems = shopItems.filter { shopItem ->
                    shopItem.name
                        .lowercase()
                        .contains(searchText.lowercase())
                }

            } else {
                filteredShopItems = shopItems
            }
        }

        VerticalSpacer(Dm.marginMedium)

        // Filters row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
//            AppDropdownMenu(
//                menuLabel = "breed",
//                fontColor = MaterialTheme.colors.secondary,
//                dropDownItems = breeds
//            ) { selectedItem ->
//                // on breed selected
//                filteredCats = cats.filter { cat ->
//                    var isMatch = false
//
//                    for (breed in cat.breeds) {
//                        if (breed.lowercase().contentEquals(selectedItem.lowercase())) {
//                            isMatch = true
//                            break
//                        }
//                    }
//
//                    isMatch
//                }
//            }

            HorizontalSpacer(Dm.marginSmall)

            AppDropdownMenu(
                menuLabel = "category",
                fontColor = MaterialTheme.colors.secondary,
                dropDownItems = listOf("A", "B", "C", "D")
            ) {
                // on category selected
            }
        }

        VerticalSpacer(Dm.marginMedium)

        // lazy Grid
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(filteredShopItems.size) { index ->
                val shopItem = filteredShopItems[index]

                ShopItemCard(
                    shopItem = shopItem,
                    cartItemCount = getCartItemCount(shopItem),
                    onIncrement = {
                        changeCartItemQuantity(shopItem, 1)
                    },
                    onDecrement = {
                        changeCartItemQuantity(shopItem, -1)
                    },
                    modifier = Modifier.padding(horizontal = Dm.marginSmall)
                ) {
                    // on card pressed
                    navController.navigate(
                        Screen.DetailsScreen.createRoute(shopItem.id)
                    )
                }
            }
        }

        VerticalSpacer(Dm.marginExtraLarge)

        // More Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(
                label = "check out",
                buttonWidth = 100.dp,
                fontColor = Color.White,
                buttonColor = MaterialTheme.colors.secondary,
            ) {
                // more button pressed
//                navController.navigate(Screen.MoreItemScreen.route)
                navController.navigate(Screen.CheckOutScreen.route)
            }
        }
    }
}




