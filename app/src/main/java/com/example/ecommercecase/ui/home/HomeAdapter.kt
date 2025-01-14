package com.example.ecommercecase.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercecase.databinding.ItemShoppingListBinding
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.utils.loadImage
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HomeAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<ProductListItem, HomeAdapter.ViewHolder>(ProductListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemShoppingListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductListItem, clickListener: ClickListener) {
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.priceShoppingItem.text = item.price
            binding.nameShoppingItem.text = item.name
            binding.imgShoppingItem.loadImage(item.image)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =ItemShoppingListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class ProductListDiffCallback : DiffUtil.ItemCallback<ProductListItem>() {

    override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((ProductListItem) -> Unit)? = null

    fun onClick(data: ProductListItem) {
        onItemClick?.invoke(data)
    }
}