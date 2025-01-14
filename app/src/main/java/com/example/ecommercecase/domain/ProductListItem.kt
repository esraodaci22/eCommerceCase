package com.example.ecommercecase.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ProductListItem(
    val createdAt: String?,
    val name: String?,
    val image: String?,
    val price: String?,
    val description: String?,
    val model: String?,
    val brand: String?,
    val id: String?
): Parcelable, Serializable
