package com.example.firebaserealtimedatabaseentegration.data

import android.os.Parcelable
import com.example.firebasedbentegration.extensions.addTL
import kotlinx.parcelize.Parcelize

data class Product(
    val productName: String,
    val productID: String,
    val productPrice: Double,
    val productRate: Float,
    val productImages: ProductImages,
    val productDescription: String,
    val productQuantity: Int,
    var isInsideBasket: Boolean,
    val productInfoList: Map<*, *>,

    )  {
    fun getPrice(): String {
        return productPrice.addTL()
    }
}

data class ProductImages(
    val image1: String,
    val image2: String
)