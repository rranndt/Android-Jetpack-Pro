package com.kotlin.submission2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.databinding.RecyclerItemCastBinding
import com.kotlin.submission2.utils.Constant
import com.kotlin.submission2.utils.Helper.setGlideCircleImages

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class CastAdapter(private val context: Context) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var cast: List<MoviesCastItem> = emptyList()

    fun setCast(cast: List<MoviesCastItem>) {
        this.cast = cast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding =
            RecyclerItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int {
        if (cast.size > 10) {
            return 10
        }
        return cast.size
    }

    class CastViewHolder(private val binding: RecyclerItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesCastItem) {
            with(itemView) {
                binding.tvName.text = data.originalName
                setGlideCircleImages(
                    context,
                    "${Constant.IMAGE_URL}${data.profilePath}",
                    binding.ivPoster
                )
            }
        }
    }
}