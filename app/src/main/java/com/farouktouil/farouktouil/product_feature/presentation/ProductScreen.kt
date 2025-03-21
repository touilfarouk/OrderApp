package com.farouktouil.farouktouil.product_feature.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.farouktouil.farouktouil.core.data.local.entities.DelivererEntity
import com.farouktouil.farouktouil.deliverer_feature.presentation.DelivererViewModel

import com.farouktouil.farouktouil.core.domain.model.Product
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val uiState by productViewModel.uiState.collectAsStateWithLifecycle()
    val isAddingProduct = remember { mutableStateOf(false) }
    val editingProduct = remember { mutableStateOf<Product?>(null) }
    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val selectedDeliverer = remember { mutableStateOf(0) }
    val deliverers by productViewModel.deliverers.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Products") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isAddingProduct.value = true
                editingProduct.value = null
                name.value = ""
                price.value = ""
                selectedDeliverer.value = 0
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Product")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (isAddingProduct.value) {
                ProductForm(
                    name = name,
                    price = price,
                    selectedDeliverer = selectedDeliverer,
                    deliverers = deliverers,
                    onSave = {
                        val priceValue = price.value.toDoubleOrNull() ?: 0.0
                        if (editingProduct.value != null) {
                            val updatedProduct = editingProduct.value!!.copy(
                                name = name.value,
                                pricePerAmount = priceValue.toFloat(),
                                belongsToDeliverer = selectedDeliverer.value.toString()
                            )
                            productViewModel.update(updatedProduct)
                        } else {
                            productViewModel.insert(name.value, priceValue, selectedDeliverer.value)
                        }
                        isAddingProduct.value = false
                    }
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.data) { product ->
                        val delivererName = deliverers.find { it.delivererId == product.belongsToDeliverer.toInt() }?.name ?: "Unknown"
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    editingProduct.value = product
                                    name.value = product.name
                                    price.value = product.pricePerAmount.toString()
                                    selectedDeliverer.value = product.belongsToDeliverer.toInt()
                                    isAddingProduct.value = true
                                },
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = product.name)
                                    Text(text = "Price: ${product.pricePerAmount}")
                                    Text(text = "Deliverer: $delivererName")
                                }
                                IconButton(onClick = {
                                    editingProduct.value = product
                                    name.value = product.name
                                    price.value = product.pricePerAmount.toString()
                                    selectedDeliverer.value = product.belongsToDeliverer.toInt()
                                    isAddingProduct.value = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        tint = Color.Blue
                                    )
                                }
                                IconButton(onClick = { productViewModel.delete(product) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductForm(
    name: MutableState<String>,
    price: MutableState<String>,
    selectedDeliverer: MutableState<Int>,
    deliverers: List<DelivererEntity>,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = price.value,
            onValueChange = { price.value = it },
            label = { Text("Price Per Unit") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        val selectedDelivererName = deliverers.find { it.delivererId == selectedDeliverer.value }?.name ?: "Select Deliverer"

        Box {
            Button(onClick = { expanded = true }) {
                Text("Deliverer: $selectedDelivererName")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                deliverers.forEach { deliverer ->
                    DropdownMenuItem(
                        text = { Text("${deliverer.name} (ID: ${deliverer.delivererId})") },
                        onClick = {
                            selectedDeliverer.value = deliverer.delivererId
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onSave, enabled = name.value.isNotEmpty() && price.value.isNotEmpty()) {
            Text(text = "Save")
        }
    }
}
