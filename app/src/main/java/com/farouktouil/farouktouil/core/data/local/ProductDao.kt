package com.farouktouil.farouktouil.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.farouktouil.farouktouil.core.data.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)



    @Update
    suspend fun updateProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products")  // ✅ Correct table name
    fun getAllProducts(): Flow<List<ProductEntity>>


    @Query("SELECT * FROM products WHERE belongsToDeliverer = :delivererId")
    fun getProductsForDeliverer(delivererId: Int): Flow<List<ProductEntity>>
}
