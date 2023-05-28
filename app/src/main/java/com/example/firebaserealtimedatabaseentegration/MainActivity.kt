package com.example.firebaserealtimedatabaseentegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.firebaserealtimedatabaseentegration.databinding.ActivityMainBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: HomeProductAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.fetchObjects()


        viewModel.productList.observeForever {
            Log.d("DamacanaDenem", it.size.toString())
            it?.let {
                adapter = HomeProductAdapter(it)
                binding.rcyProductList.adapter = adapter


            }
        }
    }
}