package com.farouktouil.farouktouil.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.farouktouil.farouktouil.order_feature.presentation.state.DelivererListItem


@Composable
fun DelivererUiListItem(
    delivererListItem: DelivererListItem,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            delivererListItem.name,
            color =  Color.Gray
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "arrow_right",
            tint =  Color.Gray
        )
    }
}