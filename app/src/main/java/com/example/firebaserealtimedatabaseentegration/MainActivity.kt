package com.example.firebaserealtimedatabaseentegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.firebaserealtimedatabaseentegration.databinding.ActivityMainBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter

class MainActivity : AppCompatActivity() {

    //    private val viewModel: HomeViewModel by viewModels()
//    lateinit var adapter: HomeProductAdapter
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //NavHostFragment needs to be updated with a new nav_graph when you have more than 1 graphs
//        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        //This will make our navController accessable from any fragment where we have a reference to mainActivity
//        navController = navHostFragment.navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // NavHostFragment'ı bağlama
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
//        navController = navHostFragment.navController


//        viewModel.fetchObjects()
//
//
//        viewModel.productList.observeForever {
//            Log.d("DamacanaDenem", it.size.toString())
//            it?.let {
//                adapter = HomeProductAdapter(it)
//                binding.rcyProductList.adapter = adapter
//
//
//            }
//        }
    }
}