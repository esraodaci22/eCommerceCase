package com.example.ecommercecase.ui.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.network.model.CartListItemModel
import com.example.ecommercecase.repository.CartListRepository
import com.example.ecommercecase.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailRepository: ProductDetailRepository,
    private val cartListRepository: CartListRepository
) : ViewModel() {
    val product = MutableLiveData<ProductListItem?>()

    fun getProduct(id: String) {
        productDetailRepository.getProduct(id)?.observeForever {
            product.value = it
        }
    }

    fun addToCart() {
        product.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                cartListRepository.insertCartListItem(CartListItemModel(
                    id = it.id ?: "",
                    title = it.name ?: "",
                    price = it.price?.toDouble() ?: 0.0,
                    image = it.image ?: "",
                    quantity = 1
                ))
            }
        }
    }
}