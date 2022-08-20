package com.nag.myamazon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nag.myamazon.composable.CartScreen
import com.nag.myamazon.model.Mobile
import com.nag.myamazon.composable.DetailsScreen
import com.nag.myamazon.composable.HomeScreen
import com.nag.myamazon.viewmodel.MobilesSharedViewModel

@Composable
fun Navigator() {

    val navController = rememberNavController()
    val sharedViewModel: MobilesSharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController,sharedViewModel)
        }

        composable(route = Screens.DetailsScreen.route) {
            DetailsScreen(navController, sharedViewModel)
        }

        composable(route = Screens.CartScreen.route) {
            CartScreen(navController, sharedViewModel)
        }



    }
}