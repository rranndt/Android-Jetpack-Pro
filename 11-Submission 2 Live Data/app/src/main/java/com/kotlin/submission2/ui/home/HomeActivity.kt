package com.kotlin.submission2.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.submission2.databinding.ActivityHomeBinding
import com.kotlin.submission2.ui.home.adapter.SliderAdapter
import com.kotlin.submission2.ui.home.adapter.ViewPagerAdapter
import com.kotlin.submission2.utils.Constant
import com.kotlin.submission2.utils.Constant.CURRENT_PAGES
import com.kotlin.submission2.utils.Constant.NUM_PAGES
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val imageItems: List<String> = listOf(
        "${Constant.IMAGE_URL}/vfuzELmhBjBTswXj2Vqxnu5ge4g.jpg",
        "${Constant.IMAGE_URL}/t8wJB7BebGYck8hgbNiZXCvBgxQ.jpg",
        "${Constant.IMAGE_URL}/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
        "${Constant.IMAGE_URL}/eCzgK2kMLBMgRCPDB9MthT4aw14.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            tabs.setupWithViewPager(binding.viewPager)
        }

        createSlider(imageItems)

    }

    private fun createSlider(listItems: List<String>) {
        binding.ivBannerSlider.adapter = SliderAdapter(this, listItems)
        NUM_PAGES = listItems.size

        val update = Runnable {
            if (CURRENT_PAGES == NUM_PAGES) {
                CURRENT_PAGES = 0
            }
            binding.ivBannerSlider.setCurrentItem(CURRENT_PAGES++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, 3000, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
