package com.example.firebaserealtimedatabaseentegration.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedbentegration.extensions.addTL
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.ItemBasketProductViewBinding
class BasketProductAdapter(
    private val onClickProductDetail: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(
            ItemBasketProductViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
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
            binding.price = mapItem.productPrice.addTL()
            binding.root.setOnClickListener {
                onClickProductDetail.invoke(mapItem)
            }
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
}