package com.kotlin.academyidleresources.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.academyidleresources.adapter.SectionsPagerAdapter
import com.kotlin.academyidleresources.databinding.ActivityHomeBinding

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