package com.example.ecommercecase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.ecommercecase.db.ProductListDao
import com.example.ecommercecase.db.asProductDomainModel
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.network.ProductListApi
import com.example.ecommercecase.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class ProductListRepository @Inject constructor(
    private val productListDao: ProductListDao,
    private val productListApi: ProductListApi
) {
    val products : LiveData<List<ProductListItem>?> = productListDao.getProductList().map { it?.asProductDomainModel() }


    suspend fun refreshProductList() {
       try {
              val productList = productListApi.getProductList()
              productListDao.insertAll(productList.asDatabaseModel())
         } catch (e: Exception) {
           Timber.w(e)
       }
    }
}