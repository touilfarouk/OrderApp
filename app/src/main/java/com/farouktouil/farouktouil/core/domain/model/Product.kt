package com.farouktouil.farouktouil.core.domain.model


import com.farouktouil.farouktouil.core.domain.SelectAndSortableByName

data class Product(
    val productId: Int=0, // Nullable for new entries
    override val name: String,
    val pricePerAmount: Float,
    val belongsToDeliverer: String
): SelectAndSortableByName