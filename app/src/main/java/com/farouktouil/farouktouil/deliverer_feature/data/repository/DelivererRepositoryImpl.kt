package com.farouktouil.farouktouil.deliverer_feature.data.repository

import com.farouktouil.farouktouil.core.data.local.DelivererDao
import com.farouktouil.farouktouil.core.data.local.ProductDao
import com.farouktouil.farouktouil.core.domain.model.Deliverer
import com.farouktouil.farouktouil.core.domain.model.Product
import com.farouktouil.farouktouil.deliverer_feature.data.mapper.toDeliverer
import com.farouktouil.farouktouil.deliverer_feature.data.mapper.toDelivererEntity
import com.farouktouil.farouktouil.product_feature.data.mapper.toProductEntity
import com.farouktouil.farouktouil.deliverer_feature.domain.repository.DelivererRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val delivererDao: DelivererDao,
    private val productDao: ProductDao
): DelivererRepository {

    override suspend fun insertDeliverers(list: List<Deliverer>) {
        list.forEach { deliverer ->
            delivererDao.insertDeliverer(deliverer.toDelivererEntity())
            insertProducts(deliverer.products, deliverer.delivererId)
        }
    }

    override suspend fun insertProducts(list: List<Product>, delivererId:Int) {
        list.forEach { product ->
            productDao.insertProduct(product.toProductEntity(delivererId))
        }
    }

    override suspend fun insert(deliverer: Deliverer) {
        delivererDao.insertDeliverer(deliverer.toDelivererEntity())
    }

    override suspend fun update(deliverer: Deliverer) {
        delivererDao.updateDeliverer(deliverer.toDelivererEntity())
    }

    override suspend fun delete(deliverer: Deliverer) {
        delivererDao.deleteDeliverer(deliverer.toDelivererEntity())
    }

    override fun getAllDeliverers(): Flow<List<Deliverer>> {
        return delivererDao.getAllDeliverers()
            .map { delivererEntities ->
                delivererEntities.map { it.toDeliverer() }
            }
    }
    override fun getAllDelivererIds(): Flow<List<Int>> {
        return delivererDao.getAllDelivererIds()
    }

}