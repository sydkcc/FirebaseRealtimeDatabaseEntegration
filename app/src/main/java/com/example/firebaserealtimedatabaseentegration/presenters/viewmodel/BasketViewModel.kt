package com.example.firebaserealtimedatabaseentegration.presenters.viewmodel

class BasketViewModel() : BaseViewModel() {

    fun deleteFromCart(id: String){
        myRef.child("product$id/isInsideBasket").setValue("0")
    }
}