package com.example.mock1exam.views.navigation

//Todo: Register your screen name here
sealed class Screen(val route: String){
    object WelcomeScreen : Screen("WelcomeScreen")

    object LoginScreen : Screen("LoginScreen")
    object SignUpScreen : Screen("SignUpScreen")

    object SearchItemListScreen : Screen("SearchItemListScreen")
    object MoreItemScreen : Screen("MoreItemsScreen")

    // Todo: If a screen require a parameter, add a createRoute method
    object DetailsScreen : Screen("DetailsScreen/{id}"){
        fun createRoute(id: String) = "DetailsScreen/$id"
    }

    object UploadScreen : Screen("UploadScreen")

    object CheckOutScreen : Screen("CheckOutScreen")
}
