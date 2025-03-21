package com.farouktouil.farouktouil.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farouktouil.farouktouil.deliverer_feature.presentation.DelivererScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderChooseDelivererScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderChooseProductsScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderScreen
import com.farouktouil.farouktouil.product_feature.presentation.ProductScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = ScreenRoutes.OrderScreen.route
    ){
        composable(ScreenRoutes.OrderScreen.route){
            OrderScreen(navController = navController)
        }
        composable(ScreenRoutes.DelivererScreen.route){
            DelivererScreen(navController = navController)
        }
        composable(ScreenRoutes.ProductScreen.route){
            ProductScreen(navController = navController)
        }

        composable(ScreenRoutes.OrderChooseDelivererScreen.route){
            OrderChooseDelivererScreen(navController = navController)
        }

        composable(ScreenRoutes.OrderChooseProductsScreen.route + "/{delivererId}") { backStackEntry ->
            val delivererId = backStackEntry.arguments?.getString("delivererId")?.toIntOrNull() ?: 0
            OrderChooseProductsScreen(
                navController = navController,
                delivererId = delivererId
            )
        }

    }

}

sealed class ScreenRoutes(val route:String){
    object OrderScreen:ScreenRoutes("order_screen")
    object OrderChooseDelivererScreen:ScreenRoutes("order_choose_deliverer_screen")
    object OrderChooseProductsScreen:ScreenRoutes("order_choose_prodcuts_screen")
    object DelivererScreen:ScreenRoutes("deliverer_screen")
    object ProductScreen:ScreenRoutes("product_screen")

}