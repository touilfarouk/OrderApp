package com.farouktouil.farouktouil.product_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farouktouil.farouktouil.core.data.local.DelivererDao
import com.farouktouil.farouktouil.core.data.local.entities.DelivererEntity
import com.farouktouil.farouktouil.core.domain.model.Product
import com.farouktouil.farouktouil.product_feature.domain.useCase.DeleteProductUseCase
import com.farouktouil.farouktouil.product_feature.domain.useCase.GetAllProductsUseCase
import com.farouktouil.farouktouil.product_feature.domain.useCase.GetProductsForDelivererUseCase
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
    private val getProductsForDelivererUseCase: GetProductsForDelivererUseCase, // UseCase to fetch products for a specific deliverer
    private val delivererDao: DelivererDao
) : ViewModel() {

    private val _selectedDelivererId = MutableStateFlow<Int?>(null)
    val selectedDelivererId: StateFlow<Int?> = _selectedDelivererId.asStateFlow()

    val uiState: StateFlow<ProductUiState> = _selectedDelivererId
        .flatMapLatest { delivererId ->
            if (delivererId == null) {
                getAllProductsUseCase.invoke() // Fetch all products if no delivererId is selected
            } else {
                getProductsForDelivererUseCase.invoke(delivererId) // Fetch products for the specific deliverer
            }
        }
        .map { ProductUiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ProductUiState())

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
            pricePerAmount = pricePerAmount.toFloat(),
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

    fun selectDeliverer(delivererId: Int?) {
        _selectedDelivererId.value = delivererId
    }
}

data class ProductUiState(
    val data: List<Product> = emptyList()
)