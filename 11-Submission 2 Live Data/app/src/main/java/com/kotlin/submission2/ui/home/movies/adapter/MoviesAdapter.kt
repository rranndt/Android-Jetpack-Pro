package com.kotlin.submission2.ui.home.movies.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.databinding.RecyclerItemGridBinding
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.ExtensionFunctions.gone
import com.kotlin.submission2.utils.Helper.setGlideImages


/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MoviesAdapter(private val context: Context, private val callback: ItemsMoviesCallback) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies: List<MoviesListItem> = emptyList()

    fun setItemList(listMovies: List<MoviesListItem>) {
        this.listMovies = listMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            RecyclerItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(listMovies[position])

    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: RecyclerItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesListItem) {
            with(itemView) {
                binding.tvTitle.text = data.title
                binding.tvRating.text = data.voteAverage.toString()

                setGlideImages(
                    context,
                    "$IMAGE_URL${data.posterPath}",
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.shimmerLayout.gone()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.shimmerLayout.gone()
                            return false
                        }

                    },
                    binding.ivHeader
                )

                binding.container.setOnClickListener {
                    callback.onItemMoviesClicked(data)
                }
            }
        }
    }

    interface ItemsMoviesCallback {
        fun onItemMoviesClicked(movies: MoviesListItem)
    }
}