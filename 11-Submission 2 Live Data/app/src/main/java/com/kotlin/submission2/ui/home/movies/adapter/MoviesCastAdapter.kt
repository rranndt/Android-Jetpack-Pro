package com.kotlin.submission2.ui.home.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.databinding.RecyclerItemCastBinding
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.Helper.setGlideCircleImages

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MoviesCastAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviesCastAdapter.CastViewHolder>() {

    private var listMoviesCast: List<MoviesCastItem> = emptyList()

    fun setMoviesCast(listMoviesCast: List<MoviesCastItem>) {
        this.listMoviesCast = listMoviesCast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding =
            RecyclerItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(listMoviesCast[position])
    }

    override fun getItemCount(): Int {
        if (listMoviesCast.size > 10) {
            return 10
        }
        return listMoviesCast.size
    }

    class CastViewHolder(private val binding: RecyclerItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesCastItem) {
            with(itemView) {
                binding.tvName.text = data.originalName
                setGlideCircleImages(
                    context,
                    "${IMAGE_URL}${data.profilePath}",
                    binding.ivPoster
                )
            }
        }
    }
}