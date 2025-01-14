package com.example.ecommercecase.ui.productdetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.ecommercecase.base.BaseFragment
import com.example.ecommercecase.databinding.FragmentProductDetailBinding
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment() {

    companion object {
        private const val ARG_PRODUCT_ID = "id"
    }

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productDetailViewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]

        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val productId = arguments?.getParcelable(ARG_PRODUCT_ID, ProductListItem::class.java)?.id
        productId?.let {
            productDetailViewModel.getProduct(it)
        }

        productDetailViewModel.product.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.nameProductDetail.text = it.name
                binding.priceProductDetail.text = it.price
                binding.descProductDetail.text = it.description
                binding.priceProductDetail.text = it.price
                binding.imgProductDetail.loadImage(it.image)
                binding.btnAddToChart.setOnClickListener {
                    productDetailViewModel.addToCart()
                }
            }
        }

        return root.rootView
    }
}