package com.kotlin.finalsub.adapter.tvseries

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.finalsub.BuildConfig
import com.kotlin.finalsub.R
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.databinding.RecyclerItemGridBinding
import com.kotlin.finalsub.utils.Helper.setImageView

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class TvSeriesFavoriteAdapter(
    private val context: Context,
    private val callback: ItemsTvSeriesCallback
) : PagedListAdapter<TvSeriesEntity, TvSeriesFavoriteAdapter.TvSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private var DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvSeriesEntity>() {
            override fun areItemsTheSame(
                oldItem: TvSeriesEntity,
                newItem: TvSeriesEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvSeriesEntity,
                newItem: TvSeriesEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TvSeriesViewHolder(val binding: RecyclerItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvSeriesEntity) {
            with(binding) {
                tvMoviesTitle.text = data.title

                if (data.voteAverage == 0.0F) {
                    tvRating.text = context.resources.getString(R.string.no_rating)
                } else {
                    tvRating.text = data.voteAverage.toString()
                }

                ratingBar.rating = data.voteAverage / 2
                setImageView(
                    ivHeader,
                    "${BuildConfig.IMAGE_URL}${data.posterPath}"
                )

                container.setOnClickListener {
                    callback.onItemTvSeriesClicked(data)
                }

                container.setOnLongClickListener {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val binding =
            RecyclerItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        val tvSeries = getItem(position)
        if (tvSeries != null) holder.bind(tvSeries)
    }
}