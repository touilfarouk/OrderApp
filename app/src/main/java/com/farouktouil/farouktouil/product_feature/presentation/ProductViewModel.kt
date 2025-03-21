package com.farouktouil.farouktouil.product_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farouktouil.farouktouil.core.data.local.DelivererDao
import com.farouktouil.farouktouil.core.data.local.entities.DelivererEntity
import com.farouktouil.farouktouil.core.domain.model.Product
import com.farouktouil.farouktouil.product_feature.domain.useCase.DeleteProductUseCase
import com.farouktouil.farouktouil.product_feature.domain.useCase.GetAllProductsUseCase
import com.farouktouil.farouktouil.product_feature.domain.useCase.InsertProductUseCase
import com.farouktouil.farouktouil.product_feature.domain.useCase.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val insertProductUseCase: InsertProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val delivererDao: DelivererDao // Inject DelivererDao
) : ViewModel() {

    val uiState =
        getAllProductsUseCase.invoke()
            .map { ProductUiState(it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, ProductUiState())

    // Fetch DelivererEntity objects instead of just IDs
    private val _deliverers = MutableStateFlow<List<DelivererEntity>>(emptyList())
    val deliverers: StateFlow<List<DelivererEntity>> = _deliverers.asStateFlow()

    init {
        fetchDeliverers()
    }

    private fun fetchDeliverers() {
        viewModelScope.launch {
            delivererDao.getAllDeliverers()
                .collect { deliverers ->
                    _deliverers.value = deliverers
                }
        }
    }

    fun insert(name: String, pricePerAmount: Double, belongsToDeliverer: Int) = viewModelScope.launch {
        val product = Product(
            name = name,
            pricePerAmount = pricePerAmount.toFloat(), // Convert Double to Float
            belongsToDeliverer = belongsToDeliverer.toString()
        )
        insertProductUseCase.invoke(product)
    }

    fun update(product: Product) = viewModelScope.launch {
        updateProductUseCase.invoke(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        deleteProductUseCase.invoke(product)
    }
}

data class ProductUiState(
    val data: List<Product> = emptyList()
)