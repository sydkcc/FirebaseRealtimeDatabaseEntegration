package com.example.firebaserealtimedatabaseentegration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

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