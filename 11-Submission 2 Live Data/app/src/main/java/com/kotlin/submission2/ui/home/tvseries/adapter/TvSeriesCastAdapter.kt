package com.kotlin.submission2.ui.home.tvseries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.databinding.RecyclerItemCastBinding
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.Helper.setGlideCircleImages

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class TvSeriesCastAdapter(private val context: Context) :
    RecyclerView.Adapter<TvSeriesCastAdapter.TvSeriesViewHolder>() {

    private var listTvSeriesCast: List<TvSeriesCastItem> = emptyList()

    fun setTvSeriesCast(listTvSeriesCast: List<TvSeriesCastItem>) {
        this.listTvSeriesCast = listTvSeriesCast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvSeriesViewHolder {
        val binding =
            RecyclerItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder.bind(listTvSeriesCast[position])
    }

    override fun getItemCount(): Int {
        if (listTvSeriesCast.size > 10) {
            return 10
        }
        return listTvSeriesCast.size
    }

    class TvSeriesViewHolder(private val binding: RecyclerItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvSeriesCastItem) {
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