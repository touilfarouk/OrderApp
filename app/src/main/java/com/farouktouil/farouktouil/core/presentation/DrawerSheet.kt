package com.farouktouil.farouktouil.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farouktouil.farouktouil.R
import com.farouktouil.farouktouil.core.util.AppConstant.APP_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DrawerSheet(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White,          // Solid White
                        Color.White.copy(alpha = 0.6f), // Semi-transparent White
                        Color.Transparent     // Fully Transparent
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f) // Adjust gradient direction
                )
            )

            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorResource(id = R.color.dark_green))
        ) {
            Text(
                APP_NAME,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight(200),
                color = colorResource(id = R.color.color_3),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Navigation options
        Column {
            TextButton(onClick = {
                scope.launch { drawerState.close() }
                navController.navigate(ScreenRoutes.OrderScreen.route)
            }) {
                Text("Orders")
            }
            TextButton(onClick = {
                scope.launch { drawerState.close() }
                navController.navigate(ScreenRoutes.DelivererScreen.route)
            }) {
                Text("Deliverers")
            }
        }
    }
}
