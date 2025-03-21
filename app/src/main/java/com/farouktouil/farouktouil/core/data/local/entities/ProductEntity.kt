package com.farouktouil.farouktouil.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey





@Entity(
    tableName = "products",
    foreignKeys = [ForeignKey(
        entity = DelivererEntity::class,
        parentColumns = ["delivererId"],
        childColumns = ["belongsToDeliverer"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val name:String,
    val pricePerAmount:Float,
    val belongsToDeliverer:String
)
