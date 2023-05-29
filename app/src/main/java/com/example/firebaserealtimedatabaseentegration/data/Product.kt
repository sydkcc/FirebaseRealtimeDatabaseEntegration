package com.example.firebaserealtimedatabaseentegration.data

import com.example.firebasedbentegration.extensions.addTL

data class Product(
    val productName: String,
    val productID: String,
    val productPrice: Double,
    val productRate: Float,
    val productImages: ProductImages,
    val productDescription: String,
    val productQuantity: Int,
    var isInsideBasket: String,
    val productInfoList: Map<*, *>,
) {
    fun getPrice(): String {
        return productPrice.addTL()
    }
}

data class ImagesModel(
    val images: ArrayList<String>? = ArrayList(),
)

data class ProductImages(
    val image1: String,
    val image2: String
)