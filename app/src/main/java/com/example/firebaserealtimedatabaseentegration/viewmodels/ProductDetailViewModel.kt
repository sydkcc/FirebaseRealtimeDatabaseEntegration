package com.example.firebaserealtimedatabaseentegration.viewmodels

class ProductDetailViewModel : BaseViewModel() {
    fun addToCart(id: String){
        myRef.child("product$id/isInsideBasket").setValue("1")
    }
}