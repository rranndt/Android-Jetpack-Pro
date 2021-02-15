package com.kotlin.submission2.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
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
        "${Constant.IMAGE_URL}/2Fk3AB8E9dYIBc2ywJkxk8BTyhc.jpg",
        "${Constant.IMAGE_URL}/9xeEGUZjgiKlI69jwIOi0hjKUIk.jpg",
        "${Constant.IMAGE_URL}/mGJuQwMq1bEboaVTqQAK4p4zQvC.jpg",
        "${Constant.IMAGE_URL}/1R6cvRtZgsYCkh8UFuWFN33xBP4.jpg",
        "${Constant.IMAGE_URL}/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
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
        binding.circleIndicator.setViewPager(binding.ivBannerSlider)
        val density = resources.displayMetrics.density
        binding.circleIndicator.radius = 5 * density
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

        binding.circleIndicator.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                CURRENT_PAGES = position
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
