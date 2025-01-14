package com.example.ecommercecase.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercecase.base.BaseFragment
import com.example.ecommercecase.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        setupRecyclerView()
        observeCartItems()

        return binding.root
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(CartAdapter.ClickListener().apply {
            onDecreaseClick = { cartViewModel.decreaseQuantity(it) }
            onIncreaseClick = { cartViewModel.increaseQuantity(it) }
            onRemoveClick = { cartViewModel.removeItem(it) }
        })
        binding.rvCartList.layoutManager = LinearLayoutManager(context)
        binding.rvCartList.adapter = cartAdapter
    }

    private fun observeCartItems() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.submitList(cartItems)
            binding.rvCartList.visibility = if (cartItems.isNullOrEmpty()) View.GONE else View.VISIBLE
            binding.tvNoItem.visibility = if (cartItems.isNullOrEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}