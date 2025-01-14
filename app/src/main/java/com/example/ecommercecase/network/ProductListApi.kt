package com.example.ecommercecase.network

import com.example.ecommercecase.network.model.ProductListItemModel
import retrofit2.http.GET

interface ProductListApi {
    @GET("products")
    suspend fun getProductList(): List<ProductListItemModel>
}