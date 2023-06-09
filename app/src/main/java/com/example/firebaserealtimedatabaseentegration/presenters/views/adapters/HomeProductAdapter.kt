package com.example.firebaserealtimedatabaseentegration.presenters.presenter.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.ItemProductViewBinding
class HomeProductAdapter(
    private val onClickAddCart: (Product) -> Unit,
    private val onClickProductDetail: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = mutableListOf<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(
            ItemProductViewBinding.inflate(
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

    fun setData(data: List<Product>) {
        items.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemHolder internal constructor(var binding: ItemProductViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mapItem: Product) {
            binding.product = mapItem
            binding.image.setOnClickListener {
                onClickProductDetail?.invoke(mapItem)
            }

            binding.addToCart.setOnClickListener {
                onClickAddCart?.invoke(mapItem)
            }
        }
    }
}