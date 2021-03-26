package com.kotlin.finalsub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kotlin.finalsub.R
import com.kotlin.finalsub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_Main)
        setContentView(binding.root)

        setupBottomNav()

    }

    private fun setupBottomNav() {
        val bottomNavView = binding.bottomMainNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        NavigationUI.setupWithNavController(
            bottomNavView,
            navHostFragment.navController
        )
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
        supportActionBar?.elevation = 0f
    }

}
