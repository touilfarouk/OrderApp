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
//----------------------------------------------------------------------------------------------------



// Retrofit
//implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
//implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
//
//import com.farouktouil.farouktouil.core.data.ApplicationApi
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitInstance {
//
//    private const val BASE_URL = "https://onta.dz/"
//
//    val api: ApplicationApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApplicationApi::class.java)
//    }
//}
//
//
//
//
//
//package com.farouktouil.farouktouil.core.data
//
//import com.farouktouil.farouktouil.core.data.remote.DelivererDto
//import com.farouktouil.farouktouil.core.data.remote.OrderDto
//import com.farouktouil.farouktouil.core.data.remote.ProductDto
//import com.farouktouil.farouktouil.core.domain.model.Deliverer
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//interface ApplicationApi {
//    @GET("deliverers")
//    suspend fun getDeliverersDto(): List<Deliverer>
//
//    @GET("deliverers/{delivererId}")
//    suspend fun getDelivererDtoById(@Path("delivererId") delivererId: Int): DelivererDto
//
//
//
//}