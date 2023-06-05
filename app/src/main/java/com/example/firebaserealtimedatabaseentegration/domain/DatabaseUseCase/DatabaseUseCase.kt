package com.example.firebaserealtimedatabaseentegration.domain.DatabaseUseCase

import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.data.database.DatabaseSource

class DatabaseUseCase {
    fun fetchObjects(callback: (List<Product>) -> Unit){
        val dbSource = DatabaseSource()
        return dbSource.fetchObjects(callback)
    }
}