package com.farouktouil.farouktouil.core.data

import com.farouktouil.farouktouil.core.data.local.entities.DelivererEntity
import com.farouktouil.farouktouil.core.data.local.entities.ProductEntity
import com.farouktouil.farouktouil.core.data.remote.DelivererDto

//fun DelivererEntity.toDto(products: List<ProductEntity>): DelivererDto {
//    return DelivererDto(
//        id = delivererId,
//        name = name,
//        products = products.map { it.toDto() }
//    )
//}
//
//fun ProductEntity.toDto(): ProductDto {
//    return ProductDto(
//        id = productId,
//        name = name,
//        pricePerAmount = pricePerAmount,
//        belongsToDeliverer = belongsToDeliverer.toIntOrNull() ?: -1
//    )
//}
//
//fun OrderEntity.toDto(
//    products: List<ProductEntity>,
//    orderProducts: List<OrderProductEntity>
//): OrderDto {
//    return OrderDto(
//        orderId = orderId,
//        date = date,
//        delivererTime = delivererTime,
//        delivererName = delivererName,
//        products = products.map { it.toDto() },
//        orderProductDetails = orderProducts.map { it.toDto() }
//    )
//}
//
//fun OrderProductEntity.toDto(): OrderProductDto {
//    return OrderProductDto(orderId, productId, amount)
//}
