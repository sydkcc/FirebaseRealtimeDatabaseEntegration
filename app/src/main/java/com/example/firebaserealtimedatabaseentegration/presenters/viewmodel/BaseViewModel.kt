package com.example.firebaserealtimedatabaseentegration.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaserealtimedatabaseentegration.data.IBasketActions
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.domain.DatabaseUseCase.DatabaseUseCase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

open class BaseViewModel() : ViewModel(), IBasketActions {

    private val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("products")
    val productList = MutableLiveData<List<Product>>()

    fun fetchObjects() {
        val dbUseCase = DatabaseUseCase()
        dbUseCase.fetchObjects { data ->
            viewModelScope.launch {
                productList.postValue(data)

            }
        }
    }

    override fun addToBasketAction(productId: String) {
        myRef.child("product$productId/isInsideBasket").setValue("1")
    }

    override fun clearBasketAction(count: Int) {
        for (id in 0 until count) {
            myRef.child("product$id/isInsideBasket").setValue("0")
        }
    }

    override fun deleteProductAction(id: String) {
        myRef.child("product$id/isInsideBasket").setValue("0")
    }
}