package com.kotlin.submission2.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kotlin.submission2.databinding.PlaceholerSliderBinding
import com.kotlin.submission2.utils.Helper.setGlideDetailsImages

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
        setGlideDetailsImages(
            context,
            listItems[position],
            binding.ivSlider
        )

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}