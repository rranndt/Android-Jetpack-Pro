package com.kotlin.submission2.ui.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.databinding.ActivityHomeBinding
import com.kotlin.submission2.ui.home.adapter.ViewPagerAdapter
import com.kotlin.submission2.utils.Constant
import com.kotlin.submission2.utils.Helper.setGlideImages
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val imageItems: List<String> = listOf(
        "${Constant.IMAGE_URL}/2Fk3AB8E9dYIBc2ywJkxk8BTyhc.jpg",
        "${Constant.IMAGE_URL}/9xeEGUZjgiKlI69jwIOi0hjKUIk.jpg",
        "${Constant.IMAGE_URL}/mGJuQwMq1bEboaVTqQAK4p4zQvC.jpg",
        "${Constant.IMAGE_URL}/1R6cvRtZgsYCkh8UFuWFN33xBP4.jpg",
        "${Constant.IMAGE_URL}/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            tabs.setupWithViewPager(binding.viewPager)

            carouselView.pageCount = imageItems.size
            carouselView.setImageListener(carouselListener)
        }

    }

    private val carouselListener = ImageListener { position, imageView ->
        setGlideImages(
            this,
            imageItems[position],
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            },
            imageView
        )
    }
}
