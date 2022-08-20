package com.nag.myamazon.navigation

sealed class Screens(val route: String){
    object HomeScreen : Screens("home_screen")
    object DetailsScreen : Screens("details_screen")
    object CartScreen : Screens("cart_screen")
}
