package com.example.firebaserealtimedatabaseentegration.presenters.presenter.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.ItemBasketProductViewBinding
import com.example.firebaserealtimedatabaseentegration.databinding.ItemEmptyBasketBinding

class BasketProductAdapter(
    private val onClickProductDetail: (Product) -> Unit,
    private val onClickProductDelete: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ItemHolder(
                ItemBasketProductViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ItemEmptyHolder(
                ItemEmptyBasketBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items.size == 0) {
            return EMPTY_VIEW
        }
        return PRODUCT_VIEW
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            holder.bind(items[position])
        } else if (holder is ItemEmptyHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemHolder internal constructor(var binding: ItemBasketProductViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mapItem: Product) {
            binding.product = mapItem
            binding.root.setOnClickListener {
                onClickProductDetail.invoke(mapItem)
            }

            binding.delete.setOnClickListener {
                onClickProductDelete.invoke(mapItem)
            }
        }
    }

    inner class ItemEmptyHolder internal constructor(var binding: ItemEmptyBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mapItem: Product) {
        }
    }

    fun setData(data: List<Product>) {
        items.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

    fun clearData() {
        items.apply {
            clear()
            notifyDataSetChanged()
        }
    }

    companion object {
        const val EMPTY_VIEW = 0
        const val PRODUCT_VIEW = 1
    }
}