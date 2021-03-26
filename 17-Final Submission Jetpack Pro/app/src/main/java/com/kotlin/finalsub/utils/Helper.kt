package com.kotlin.finalsub.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.kotlin.finalsub.R
import com.kotlin.finalsub.data.source.remote.response.movies.detail.MoviesDetailItem
import com.kotlin.finalsub.data.source.remote.response.tv.detail.TvSeriesDetailItem
import java.text.SimpleDateFormat


/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Helper {

    fun setImageView(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            ImageView.ScaleType.FIT_XY
            placeholder(R.drawable.ic_loading_placeholder)
            error(R.drawable.ic_error)
        }
    }

    fun setImageViewRound(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            transformations(CircleCropTransformation())
            placeholder(R.drawable.ic_loading_placeholder)
            error(R.drawable.ic_error)
        }
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

//    fun joinGenres(movie: MoviesDetailItem) = movie.genres.joinToString(", ")

    fun joinGenres(movie: MoviesDetailItem): String {
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
        return genre.toString()
    }

//    fun joinGenres(tvSeries: TvSeriesDetailItem) = tvSeries.genres.joinToString(", ")

    fun joinGenres(tvSeries: TvSeriesDetailItem): String {
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
        return genre.toString()
    }

}