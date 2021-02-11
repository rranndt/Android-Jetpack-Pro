package com.kotlin.submission2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.submission2.R
import com.kotlin.submission2.databinding.ActivityHomeBinding
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    val imageBackground = intArrayOf(
        R.drawable.bg_joker,
        R.drawable.bg_6_underground,
        R.drawable.bg_avenger_end_game,
        R.drawable.bg_godzilla,
        R.drawable.bg_hobbs_shaw,
        R.drawable.bg_jumanji
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBanner.pageCount = imageBackground.size
        binding.ivBanner.setImageListener(imageListener)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(imageBackground[position])
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}