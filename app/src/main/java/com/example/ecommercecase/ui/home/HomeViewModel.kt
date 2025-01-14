package com.example.ecommercecase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercecase.repository.CartListRepository
import com.example.ecommercecase.repository.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val productListRepository: ProductListRepository,
    private val cartListRepository: CartListRepository
): ViewModel() {

    val data = productListRepository.products

    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String>
        get() = _navigateToDetail

    fun onItemClicked(id: String) {
        _navigateToDetail.value = id
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }

    fun loadMore() {
        //can be add pager and paging source
        viewModelScope.launch(Dispatchers.IO) {
            productListRepository.refreshProductList()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productListRepository.refreshProductList()
        }
    }


}