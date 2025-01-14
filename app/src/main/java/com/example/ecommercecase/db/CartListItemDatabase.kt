package com.example.ecommercecase.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CartListItemDatabase")
data class CartListItemDatabase(
    @PrimaryKey val id: String,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int
)