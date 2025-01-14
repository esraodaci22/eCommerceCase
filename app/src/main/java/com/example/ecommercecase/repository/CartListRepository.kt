package com.example.ecommercecase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.ecommercecase.db.CartListItemDao
import com.example.ecommercecase.db.asCartDomainModel
import com.example.ecommercecase.network.model.CartListItemModel
import com.example.ecommercecase.network.model.asDatabaseModel
import javax.inject.Inject

class CartListRepository @Inject constructor(
    private val cartListItemDao: CartListItemDao
) {
    val cart = cartListItemDao.getCartList()


    val cartList: LiveData<List<CartListItemModel>?> = cartListItemDao.getCartList().map { it?.asCartDomainModel() }

    suspend fun insertCartListItem(cartListItem: CartListItemModel) {
        cartListItemDao.insertCartListItem(cartListItem.asDatabaseModel())
    }

    suspend fun deleteCartListItem(cartListItem: CartListItemModel) {
        cartListItemDao.deleteCartListItem(cartListItem.id)
    }

    suspend fun updateCartListItem(cartListItem: CartListItemModel) {
        cartListItemDao.updateCartListItem(cartListItem.id, cartListItem.quantity)
    }

    suspend fun deleteAllCartListItems() {
        cartListItemDao.deleteAllCartListItems()
    }
}