package com.example.ecommercecase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercecase.R
import com.example.ecommercecase.base.BaseFragment
import com.example.ecommercecase.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        recyclerView = binding.rvShoppingList
        homeAdapter = HomeAdapter(ClickListener())
        recyclerView.layoutManager = GridLayoutManager(this@HomeFragment.context, 2)
        recyclerView.adapter = homeAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (totalItemCount <= (lastVisibleItem + 2)) {
                    homeViewModel.loadMore()
                }
            }
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.data.observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)

        }
        homeAdapter.clickListener.onItemClick = {
            homeViewModel.apply {
                it.id?.let { it1 -> onItemClicked(it1) }
                onDetailNavigated()
            }
            val bundle = Bundle().apply {
                putParcelable("id", it)
            }
            findNavController().navigate(R.id.action_navigation_home_to_navigation_product_detail, bundle)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}