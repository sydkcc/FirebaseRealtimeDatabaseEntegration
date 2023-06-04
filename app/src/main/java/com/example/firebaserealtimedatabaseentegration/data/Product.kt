package com.example.firebaserealtimedatabaseentegration.data

import com.example.firebasedbentegration.extensions.format
import com.example.firebaserealtimedatabaseentegration.extensions.addTL

data class Product(
    val productName: String,
    val productID: String,
    val productPrice: Double,
    val productRate: Double,
    val productImages: ProductImages,
    val productDescription: String,
    val productQuantity: Int,
    var isInsideBasket: String,
    val productInfoList: Map<*, *>,
) {
    fun getPrice(): String {
        return productPrice.format(2).addTL()
    }

    fun getRate(): Float {
        return productRate.toFloat()
    }
}

data class ProductImages(
    val image1: String,
    val image2: String
)