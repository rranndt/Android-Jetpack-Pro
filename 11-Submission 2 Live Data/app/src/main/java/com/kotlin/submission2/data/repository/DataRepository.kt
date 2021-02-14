package com.kotlin.submission2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailResponse
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

    override fun getMoviesDetail(moviesId: String): LiveData<MoviesDetailResponse> {
        val moviesDetail = MutableLiveData<MoviesDetailResponse>()
        remoteDataSource.getMoviesDetail(moviesId, object : RemoteDataSource.GetMoviesDetailCallback {
            override fun onResponse(moviesDetailResponse: MoviesDetailResponse) {
                moviesDetail.postValue(moviesDetailResponse)
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

    override fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetailResponse> {
        val tvSeriesDetail = MutableLiveData<TvSeriesDetailResponse>()
        remoteDataSource.getTvSeriesDetail(tvSeriesId, object : RemoteDataSource.GetTvSeriesDetailCallback {
            override fun onResponse(tvSeriesDetailResponse: TvSeriesDetailResponse) {
                tvSeriesDetail.postValue(tvSeriesDetailResponse)
            }
        })
        return tvSeriesDetail
    }

}