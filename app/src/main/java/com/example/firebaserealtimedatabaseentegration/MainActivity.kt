package com.example.firebaserealtimedatabaseentegration

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
    companion object {
        const val ANIMATION_TIME: Long = 4000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Handler(this.mainLooper).postDelayed({
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }, ANIMATION_TIME)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


    }
}