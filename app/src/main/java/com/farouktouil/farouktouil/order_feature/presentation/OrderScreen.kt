package com.farouktouil.farouktouil.order_feature.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farouktouil.farouktouil.core.presentation.ScreenRoutes
import com.farouktouil.farouktouil.export_feature.presentation.ExportScreen
import com.farouktouil.farouktouil.order_feature.presentation.components.OrderDetailDialog
import com.farouktouil.farouktouil.order_feature.presentation.components.OrderUiListItem
import com.farouktouil.farouktouil.ui.theme.primaryContainerLight
import com.farouktouil.farouktouil.ui.theme.primaryLight


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(
    navController: NavController,
    orderViewModel: OrderViewModel = hiltViewModel()
) {

   // val scaffoldState = rememberScaffoldState()

    Scaffold(
       // scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(ScreenRoutes.OrderChooseDelivererScreen.route)
                },
                //background = orange
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "fab_add_order",
                   // tint = white
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Order overview",
                      //  color = white
                    )
                },
               // backgroundColor = orange
            )
        }
    ){
        if(orderViewModel.orderList.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                //Text("There are no orders yet")
                ExportScreen()
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                   // .background(gray)
                    .padding(top = 80.dp, start = 15.dp, end = 15.dp, bottom = 15.dp), // Adjust top padding

                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(
                    orderViewModel.orderList,
                    key = {orderListItem ->
                        orderListItem.orderId
                    }
                ){
                    OrderUiListItem(
                        it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, color = primaryLight, RoundedCornerShape(10.dp))
                            .clickable {
                                orderViewModel.onOrderClick(it.orderId)
                            }
                            .padding(15.dp)
                    )
                }
            }
        }
    }

    if(orderViewModel.isOrderDialogShown && orderViewModel.clickedOrderItem != null){
        OrderDetailDialog(
            onDismiss = {
                        orderViewModel.onDismissOrderDialog()
            },
            orderDetailListItem = orderViewModel.clickedOrderItem!!
        )
    }

}