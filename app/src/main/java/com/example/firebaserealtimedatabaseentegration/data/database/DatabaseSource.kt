package com.example.firebaserealtimedatabaseentegration.data.database

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.data.ProductImages
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseSource {

    val productList = MutableLiveData<List<Product>>()
    private val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("products")

    fun fetchObjects(callback: (List<Product>) -> Unit) {
        var data: MutableList<Product> = mutableListOf()
        var filteredItems: MutableList<Product> = mutableListOf()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: List<String> = ArrayList()

                snapshot.children.forEach { child ->
                    val productDict = child.getValue() as? Map<*, *>
                    val productName = productDict?.get("productName") as? String
                    val productPrice = productDict?.get("productPrice") as? Double
                    val productRate = productDict?.get("productRate") as? Double
                    val productImagesDict = productDict?.get("productImages") as? Map<*, *>
                    val productID = productDict?.get("productID") as? String
                    val productIsInsideBasket = productDict?.get("isInsideBasket") as? String
                    val productDescription = productDict?.get("productDescription") as? String
                    val productQuantity = productDict?.get("productQuantity") as? Int
                    val productInfoList = productDict?.get("productInfoList") as? Map<*, *>

                    val productImages = ProductImages(
                        image1 = productImagesDict?.get("image1") as? String ?: "",
                        image2 = productImagesDict?.get("image2") as? String ?: ""
                    )
                    val product = Product(
                        productName = productName ?: "",
                        productID = productID ?: "",
                        productPrice = productPrice ?: 0.0,
                        productRate = productRate ?: 0.0,
                        productImages = productImages,
                        productDescription = productDescription ?: "",
                        productQuantity = productQuantity ?: 0,
                        isInsideBasket = productIsInsideBasket ?: "0",
                        productInfoList = productInfoList ?: mutableMapOf<Any?, Any?>()
                    )
                    data.add(product)
                }
                filteredItems = data
                callback(filteredItems)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("", error.message);
            }
        })
    }

}