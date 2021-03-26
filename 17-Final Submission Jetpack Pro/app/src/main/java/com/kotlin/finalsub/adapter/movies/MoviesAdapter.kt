package com.kotlin.finalsub.adapter.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.finalsub.BuildConfig.IMAGE_URL
import com.kotlin.finalsub.R
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.databinding.RecyclerItemGridBinding
import com.kotlin.finalsub.utils.Helper.setImageView


/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MoviesAdapter(private val context: Context, private val callback: ItemsMoviesCallback) :
    PagedListAdapter<MoviesEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            RecyclerItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) holder.bind(movies)
    }

    inner class MoviesViewHolder(private val binding: RecyclerItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesEntity) {
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
                    "${IMAGE_URL}${data.posterPath}"
                )

                container.setOnClickListener {
                    callback.onItemMoviesClicked(data)
                }

                container.setOnLongClickListener {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

}