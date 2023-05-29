package com.example.firebaserealtimedatabaseentegration.viewmodels

import androidx.lifecycle.ViewModel

class BasketViewModel : BaseViewModel() {
    fun clearBasket(){
        for (id in 0 until 10) {
            myRef.child("product$id/isInsideBasket").setValue("0")
        }

    }
}