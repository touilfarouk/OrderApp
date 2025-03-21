package com.farouktouil.farouktouil.order_feature.domain.use_case

import android.annotation.SuppressLint
import com.farouktouil.farouktouil.order_feature.domain.model.BoughtProduct
import com.farouktouil.farouktouil.order_feature.domain.model.Order
import com.farouktouil.farouktouil.order_feature.domain.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

class ConfirmOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {

    @SuppressLint("NewApi")
    operator fun invoke(products:List<BoughtProduct>, delivererId:Int){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        val formatter:DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        val date = formatter.format(LocalDateTime.now())
        coroutineScope.launch {
            val delivererName = orderRepository.getDelivererNameById(delivererId)
            orderRepository.insertOrder(
                order = Order(
                    orderId = UUID.randomUUID().toString(),
                    date = date,
                    delivererTime = "As fast as possible",
                    delivererName = delivererName,
                    products = products
                )
            )
        }
    }

}