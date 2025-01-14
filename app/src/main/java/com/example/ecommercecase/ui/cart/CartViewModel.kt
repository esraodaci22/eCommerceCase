package com.example.ecommercecase.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercecase.network.model.CartListItemModel
import com.example.ecommercecase.repository.CartListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartListRepository: CartListRepository
) : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartListItemModel>>()
    val cartItems: LiveData<List<CartListItemModel>> get() = _cartItems

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        _cartItems.value = cartListRepository.cartList.value
    }

    fun increaseQuantity(item: CartListItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedItem = item.copy(quantity = item.quantity + 1)
            cartListRepository.updateCartListItem(updatedItem)
            _cartItems.value = cartListRepository.cartList.value
        }
    }

    fun decreaseQuantity(item: CartListItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            if (item.quantity > 1) {
                val updatedItem = item.copy(quantity = item.quantity - 1)
                cartListRepository.updateCartListItem(updatedItem)
                _cartItems.value = cartListRepository.cartList.value
            }
        }
    }

    fun removeItem(item: CartListItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cartListRepository.deleteCartListItem(item)
            _cartItems.value = cartListRepository.cartList.value
        }
    }
}