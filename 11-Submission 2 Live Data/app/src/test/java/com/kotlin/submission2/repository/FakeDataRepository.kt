package com.kotlin.submission2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.data.source.DataSource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class FakeDataRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

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

    override fun getMoviesCast(moviesId: String): LiveData<List<MoviesCastItem>> {
        val moviesItemCast = MutableLiveData<List<MoviesCastItem>>()
        remoteDataSource.getMoviesCast(
            moviesId,
            object : RemoteDataSource.GetMoviesCastCallback {
                override fun onResponse(moviesCast: List<MoviesCastItem>) {
                    moviesItemCast.postValue(moviesCast)
                }
            }
        )
        return moviesItemCast
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

    override fun getTvSeriesCast(tvSeriesId: String): LiveData<List<TvSeriesCastItem>> {
        val tvSeriesItemCast = MutableLiveData<List<TvSeriesCastItem>>()
        remoteDataSource.getTvSeriesCast(
            tvSeriesId,
            object : RemoteDataSource.GetTvSeriesCastCallback {
                override fun onResponse(tvSeriesCast: List<TvSeriesCastItem>) {
                    tvSeriesItemCast.postValue(tvSeriesCast)
                }
            }
        )
        return tvSeriesItemCast
    }

}