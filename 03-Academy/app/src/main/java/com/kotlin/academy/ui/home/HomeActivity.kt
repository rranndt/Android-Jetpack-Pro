package com.kotlin.academy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.academy.R
import com.kotlin.academy.adapter.SectionsPagerAdapter
import com.kotlin.academy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}