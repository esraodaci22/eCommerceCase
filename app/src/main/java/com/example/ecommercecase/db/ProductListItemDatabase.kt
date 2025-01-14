package com.example.ecommercecase.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.utils.toCurrencyFormat

@Entity("ProductListItemDatabase")
data class ProductListItemDatabase(
    @PrimaryKey
    val id: String,
    val createdAt: String,
    val name: String,
    val image: String,
    val price: String,
    val description: String,
    val model: String,
    val brand: String
)