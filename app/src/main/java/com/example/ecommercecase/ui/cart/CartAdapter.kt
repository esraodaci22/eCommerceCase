package com.example.ecommercecase.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercecase.databinding.ItemCartListBinding
import com.example.ecommercecase.network.model.CartListItemModel
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class CartAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<CartListItemModel, CartAdapter.ViewHolder>(CartListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: ItemCartListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartListItemModel, clickListener: ClickListener) {
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.nameCartList.text = item.title
            binding.priceCartList.text = item.price.toString()
            binding.quantityCartList.text = item.quantity.toString()
            binding.btnDecrease.setOnClickListener {
                clickListener.onDecreaseClick(item)
            }
            binding.btnIncrease.setOnClickListener {
                clickListener.onIncreaseClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCartListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class CartListDiffCallback : DiffUtil.ItemCallback<CartListItemModel>() {
        override fun areItemsTheSame(oldItem: CartListItemModel, newItem: CartListItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartListItemModel, newItem: CartListItemModel): Boolean {
            return oldItem == newItem
        }
    }

    class ClickListener @Inject constructor() {
        var onItemClick: ((CartListItemModel) -> Unit)? = null
        var onDecreaseClick: ((CartListItemModel) -> Unit)? = null
        var onIncreaseClick: ((CartListItemModel) -> Unit)? = null
        var onRemoveClick: ((CartListItemModel) -> Unit)? = null

        fun onClick(data: CartListItemModel) {
            onItemClick?.invoke(data)
        }

        fun onDecreaseClick(data: CartListItemModel) {
            onDecreaseClick?.invoke(data)
        }

        fun onIncreaseClick(data: CartListItemModel) {
            onIncreaseClick?.invoke(data)
        }

        fun onRemoveClick(data: CartListItemModel) {
            onRemoveClick?.invoke(data)
        }
    }
}