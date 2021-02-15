package com.kotlin.submission2.ui.home.tvseries.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.databinding.RecyclerItemGridBinding
import com.kotlin.submission2.utils.Constant
import com.kotlin.submission2.utils.Helper.setGlideImages

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class TvSeriesAdapter(private val context: Context, private val callback: ItemsTvSeriesCallback) :
    RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>() {

    private var listTvSeries: List<TvSeriesListItem> = emptyList()

    fun setItemList(listTvSeries: List<TvSeriesListItem>) {
        this.listTvSeries = listTvSeries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val binding =
            RecyclerItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder.bind(listTvSeries[position])
    }

    override fun getItemCount(): Int = listTvSeries.size

    inner class TvSeriesViewHolder(private val binding: RecyclerItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvSeriesListItem) {
            with(itemView) {
                binding.tvTitle.text = data.originalName
                binding.tvRating.text = data.voteAverage.toString()

                setGlideImages(
                    context,
                    "${Constant.IMAGE_URL}${data.posterPath}",
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.shimmerLayout.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.shimmerLayout.visibility = View.GONE
                            return false
                        }

                    },
                    binding.ivHeader
                )

                binding.container.setOnClickListener {
                    callback.onItemTvSeriesClicked(data)
                }
            }
        }
    }

    interface ItemsTvSeriesCallback {
        fun onItemTvSeriesClicked(tvSeries: TvSeriesListItem)
    }
}