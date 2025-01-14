package com.example.ecommercecase.network.model

import com.example.ecommercecase.db.ProductListItemDatabase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListItemModel(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "model")
    val model: String,
    @Json(name = "brand")
    val brand: String,
    @Json(name = "id")
    val id: String
)


fun List<ProductListItemModel>.asDatabaseModel(): List<ProductListItemDatabase> {
   return map {
            ProductListItemDatabase(
                createdAt = it.createdAt,
                name = it.name,
                image = it.image,
                price = it.price,
                description = it.description,
                model = it.model,
                brand = it.brand,
                id = it.id
            )
        }
}