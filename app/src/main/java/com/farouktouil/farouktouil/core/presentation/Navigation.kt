package com.farouktouil.farouktouil.core.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farouktouil.farouktouil.deliverer_feature.presentation.DelivererScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderChooseDelivererScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderChooseProductsScreen
import com.farouktouil.farouktouil.order_feature.presentation.OrderScreen
import com.farouktouil.farouktouil.product_feature.presentation.ProductScreen
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = Modifier

            .fillMaxHeight(),

        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(navController, drawerState, scope)
        },
        gesturesEnabled = true // Ensuring proper swipe behavior
    ) {
        NavHost(
            navController,
            startDestination = ScreenRoutes.OrderScreen.route
        ) {
            composable(ScreenRoutes.OrderScreen.route) {
                OrderScreen(navController = navController, drawerState = drawerState, scope = scope)
            }
            composable(ScreenRoutes.DelivererScreen.route) {
                DelivererScreen(navController = navController, drawerState = drawerState, scope = scope)
            }
            composable(
                route = ScreenRoutes.ProductScreen.route + "/{delivererId}",
                arguments = listOf(navArgument("delivererId") { type = NavType.IntType })
            ) { backStackEntry ->
                val delivererId = backStackEntry.arguments?.getInt("delivererId")
                ProductScreen(
                    navController = navController,
                    delivererId = delivererId, drawerState = drawerState, scope = scope
                )
            }

            composable(ScreenRoutes.OrderChooseDelivererScreen.route) {
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
}


sealed class ScreenRoutes(val route:String){
    object OrderScreen:ScreenRoutes("order_screen")
    object OrderChooseDelivererScreen:ScreenRoutes("order_choose_deliverer_screen")
    object OrderChooseProductsScreen:ScreenRoutes("order_choose_products_screen")
    object DelivererScreen:ScreenRoutes("deliverer_screen")
    object ProductScreen:ScreenRoutes("product_screen")

}