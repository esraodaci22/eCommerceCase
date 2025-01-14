package com.example.ecommercecase.network.model

import com.example.ecommercecase.db.CartListItemDatabase
import com.example.ecommercecase.domain.ProductListItem

data class CartListItemModel(
    val id: String,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int
)

fun CartListItemModel.asDatabaseModel(): CartListItemDatabase {
    return CartListItemDatabase(
        id = this.id,
        title = this.title,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}