package com.kotlin.submission2.ui.home.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.databinding.PlaceholerSliderBinding
import com.kotlin.submission2.utils.Helper.setGlideImages

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class SliderAdapter(private val context: Context, private val listItems: List<String>) :
    PagerAdapter() {

    override fun getCount(): Int = listItems.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = PlaceholerSliderBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        )
        setGlideImages(
            context,
            listItems[position],
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
            binding.ivSlider
        )

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}