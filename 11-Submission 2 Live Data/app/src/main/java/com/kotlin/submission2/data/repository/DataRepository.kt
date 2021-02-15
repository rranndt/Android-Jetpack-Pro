package com.kotlin.submission2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.data.source.DataSource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class DataRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): DataRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataRepository(remoteDataSource)
            }
    }

    override fun getMovies(): LiveData<List<MoviesListItem>> {
        val moviesItemList = MutableLiveData<List<MoviesListItem>>()
        remoteDataSource.getMovies(object : RemoteDataSource.GetMoviesCallback {
            override fun onResponse(moviesResponse: List<MoviesListItem>) {
                moviesItemList.postValue(moviesResponse)
            }
        })
        return moviesItemList
    }

    override fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailItem> {
        val moviesDetail = MutableLiveData<MoviesDetailItem>()
        remoteDataSource.getMoviesDetail(
            moviesId,
            object : RemoteDataSource.GetMoviesDetailCallback {
                override fun onResponse(moviesDetailItem: MoviesDetailItem) {
                    moviesDetail.postValue(moviesDetailItem)
                }
            })
        return moviesDetail
    }

    override fun getTvSeries(): LiveData<List<TvSeriesListItem>> {
        val tvSeriesItemList = MutableLiveData<List<TvSeriesListItem>>()
        remoteDataSource.getTvSeries(object : RemoteDataSource.GetTvSeriesCallback {
            override fun onResponse(tvSeriesResponse: List<TvSeriesListItem>) {
                tvSeriesItemList.postValue(tvSeriesResponse)
            }
        })
        return tvSeriesItemList
    }

    override fun getCast(moviesId: String): LiveData<List<MoviesCastItem>> {
        val cast = MutableLiveData<List<MoviesCastItem>>()
        remoteDataSource.getMoviesCast(
            moviesId,
            object : RemoteDataSource.GetCastCallback {
                override fun onResponse(moviesCast: List<MoviesCastItem>) {
                    cast.postValue(moviesCast)
                }
            }
        )
        return cast
    }

    override fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailItem> {
        val tvSeriesDetail = MutableLiveData<TvSeriesDetailItem>()
        remoteDataSource.getTvSeriesDetail(
            tvSeriesId,
            object : RemoteDataSource.GetTvSeriesDetailCallback {
                override fun onResponse(tvSeriesDetailItem: TvSeriesDetailItem) {
                    tvSeriesDetail.postValue(tvSeriesDetailItem)
                }
            })
        return tvSeriesDetail
    }

}