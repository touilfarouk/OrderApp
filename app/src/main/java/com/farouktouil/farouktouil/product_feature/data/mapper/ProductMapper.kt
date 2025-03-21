package com.farouktouil.farouktouil.product_feature.data.mapper

import com.farouktouil.farouktouil.core.data.local.entities.ProductEntity
import com.farouktouil.farouktouil.core.domain.model.Product

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        productId = productId ?: 0, // Use 0 if null
        name = name,
        pricePerAmount = pricePerAmount,
        belongsToDeliverer = (belongsToDeliverer.toIntOrNull() ?: 0).toString() // Convert String to Int
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        productId = productId, // Non-nullable in ProductEntity
        name = name,
        pricePerAmount = pricePerAmount,
        belongsToDeliverer = belongsToDeliverer.toString() // Convert Int to String
    )
}

