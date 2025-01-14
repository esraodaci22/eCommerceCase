package com.example.ecommercecase.repository

import com.example.ecommercecase.db.ProductListDao
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val productListDao: ProductListDao){
    fun getProduct(id: String) = productListDao.getProductFromList(id)
}