package com.farouktouil.farouktouil.deliverer_feature.data.mapper

import com.farouktouil.farouktouil.core.data.local.entities.DelivererEntity
import com.farouktouil.farouktouil.core.domain.model.Deliverer



fun Deliverer.toDelivererEntity(): DelivererEntity {
    return DelivererEntity(
        delivererId = delivererId ?: 0, // Use 0 if null
        name = name
    )
}

fun DelivererEntity.toDeliverer(): Deliverer {
    return Deliverer(
        delivererId = delivererId,
        name = name,
        products = emptyList() // Add logic to fetch products if needed
    )
}