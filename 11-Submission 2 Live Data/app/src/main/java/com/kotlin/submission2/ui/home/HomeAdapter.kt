package com.kotlin.submission2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.kotlin.submission2.R
import com.kotlin.submission2.databinding.ItemGridBinding
import com.kotlin.submission2.model.DataEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeAdapter(private val callback: ItemCallback) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val itemList = ArrayList<DataEntity>()

    fun setItemList(item: List<DataEntity>?) {
        if (item == null) return
        itemList.clear()
        itemList.addAll(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemGridbinding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemGridbinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class HomeViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataEntity) {
            with(itemView) {
                binding.tvTitle.text = data.title
                binding.tvRating.text = data.rating

                Glide.with(context)
                    .load(data.bgPoster)
                    .error(R.drawable.ic_error)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivHeader)

                binding.container.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }
        }
    }

    interface ItemCallback {
        fun onItemClicked(data: DataEntity)
    }
}