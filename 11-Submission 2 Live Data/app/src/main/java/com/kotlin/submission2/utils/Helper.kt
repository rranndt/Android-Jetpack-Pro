package com.kotlin.submission2.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.kotlin.submission2.R
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailResponse
import java.text.SimpleDateFormat


/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Helper {

    private fun glideLoadingPlaceholder(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 50f
            start()
        }
    }

    fun setGlideImages(
        context: Context,
        loadImage: String,
        view: RequestListener<Drawable>,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(loadImage)
            .error(R.drawable.ic_error)
            .placeholder(glideLoadingPlaceholder(context))
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(view)
            .skipMemoryCache(true)
            .into(imageView)
    }

    fun setGlideDetailsImages(
        context: Context,
        loadImage: String,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(loadImage)
            .error(R.drawable.ic_error)
            .placeholder(glideLoadingPlaceholder(context))
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(RoundedCorners(20))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .into(imageView)
    }

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(
        currentFormat: String,
        requiredFormat: String,
        dateString: String
    ): String {
        val oldFormatDate = SimpleDateFormat(currentFormat)
        val newFormatDate = SimpleDateFormat(requiredFormat)
        val updateDate = oldFormatDate.parse(dateString)
        return newFormatDate.format(updateDate!!)
    }

    fun joinGenres(movie: MoviesDetailResponse): StringBuilder {
        val genre = StringBuilder()
        for (i in movie.genres.indices) {
            if (i == 0) {
                genre.append(
                    movie.genres[i].name
                )
            } else {
                genre.append(", ").append(movie.genres[i].name)
            }
        }
        return genre
    }

    fun joinGenres(tvSeries: TvSeriesDetailResponse): StringBuilder {
        val genre = StringBuilder()
        for (i in tvSeries.genres.indices) {
            if (i == 0) {
                genre.append(
                    tvSeries.genres[i].name
                )
            } else {
                genre.append(", ").append(tvSeries.genres[i].name)
            }
        }
        return genre
    }

}