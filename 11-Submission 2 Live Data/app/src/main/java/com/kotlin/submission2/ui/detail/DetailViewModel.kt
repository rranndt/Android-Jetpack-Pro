package com.kotlin.submission2.ui.detail

import androidx.lifecycle.ViewModel
import com.kotlin.submission2.data.DataEntity
import com.kotlin.submission2.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class DetailViewModel : ViewModel() {

    private lateinit var movies: String
    private lateinit var tvSeries: String

    fun setMovies(movies: String) {
        this.movies = movies
    }

    fun setTvSeries(tvSeries: String) {
        this.tvSeries = tvSeries
    }

    fun getMovies(): DataEntity {
        lateinit var movies: DataEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (moviesEntity in moviesEntities) {
            if (moviesEntity.id == this.movies) {
                movies = moviesEntity
                break
            }
        }
        return movies
    }

    fun getTvSeries(): DataEntity {
        lateinit var tvSeries: DataEntity
        val tvSeriesEntities = DataDummy.generateDummyTvSeries()
        for (tvSeriesEntity in tvSeriesEntities) {
            if (tvSeriesEntity.id == this.tvSeries) {
                tvSeries = tvSeriesEntity
                break
            }
        }
        return tvSeries
    }

}