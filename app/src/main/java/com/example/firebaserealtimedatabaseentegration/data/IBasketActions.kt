package com.example.firebaserealtimedatabaseentegration.data

interface IBasketActions {
    fun addToBasketAction(productId: String)
    fun clearBasketAction(count: Int)
    fun deleteProductAction(id: String)
}