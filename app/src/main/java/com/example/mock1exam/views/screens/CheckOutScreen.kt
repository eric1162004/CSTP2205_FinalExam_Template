package com.example.mock1exam.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodvillage2205.model.entities.User
import com.example.foodvillage2205.model.repositories.UserRepository
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.CartItem
import com.example.mock1exam.backend.models.ShopItem
import com.example.mock1exam.backend.repositories.ShopItemRepository
import com.example.mock1exam.ui.theme.Dm
import com.example.mock1exam.utils.auth.Auth
import com.example.mock1exam.views.app_reusables.ShopItemCard
import com.example.mock1exam.views.navigation.Screen
import com.example.mock1exam.views.reusables.AppButton
import com.example.mock1exam.views.reusables.Drawer
import com.example.mock1exam.views.reusables.HorizontalSpacer
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun CheckOutScreen(
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
                            text = "CHECK",
                            style = MaterialTheme.typography.body1,
                            fontSize = 40.sp,
                            color = MaterialTheme.colors.primary
                        )

                        HorizontalSpacer(Dm.marginSmall)

                        Text(
                            text = "OUT",
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
        CheckOutScreenContent(navController)
    }
}

@ExperimentalFoundationApi
@Composable
private fun CheckOutScreenContent(
    navController: NavController
) {
    val shopItemRepository by remember { mutableStateOf(ShopItemRepository()) }

    var auth by remember { mutableStateOf(Auth()) }
    var userRepository by remember { mutableStateOf(UserRepository()) }
    var currentUser by remember { mutableStateOf(User()) }
    var shoppingCart by remember { mutableStateOf(mutableListOf<CartItem>()) }

    var errorMessage by remember { mutableStateOf("") }

    // Get initial data from firebase
    LaunchedEffect(key1 = true) {
        // temp fix: delay 2 secs to get the updated values
        delay(2000L)

        // get user info
        userRepository.getUserById(auth.currentUser!!.uid) {
            if (it is Resource.Success<*>) {
                currentUser = it.data as User
                shoppingCart = currentUser.cartItems
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
            userRepository.updateUser(
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
        // lazy Grid
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(shoppingCart.size) { index ->
                val shopItem = shoppingCart[index].shopItem

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

        // Confirm Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(
                label = "confirm",
                buttonWidth = 100.dp,
                fontColor = Color.White,
                buttonColor = MaterialTheme.colors.secondary,
            ) {
                // decrement inventory
                shoppingCart.forEach {
                    var shopItem = it.shopItem
                    shopItem.count -= it.wantedQuantity
                    shopItemRepository.update(shopItem) { resource ->
                        if (resource is Resource.Success<*>) {
                            // clear item from shopping cart
                            shoppingCart.remove(it)
                        }
                    }
                }
                navController.navigate(Screen.SearchItemListScreen.route)
            }
        }
    }
}