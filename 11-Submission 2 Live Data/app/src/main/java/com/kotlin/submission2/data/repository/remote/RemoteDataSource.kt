package com.kotlin.submission2.data.repository.remote

import android.util.Log
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailResponse
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListResponse
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailResponse
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesResponse
import com.kotlin.submission2.network.ApiConfig
import com.kotlin.submission2.utils.Constant.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class RemoteDataSource(apiConfig: ApiConfig) {

    private val apiConfig = ApiConfig

    companion object {
        private var INSTANCE: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.simpleName

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource {
            if (INSTANCE == null)
                INSTANCE = RemoteDataSource(apiConfig)
            return INSTANCE!!
        }
    }

    fun getMovies(getMoviesCallback: GetMoviesCallback) {
        val client = apiConfig.getApiService().getMovies(API)
        client.enqueue(object : Callback<MoviesListResponse> {
            override fun onResponse(
                call: Call<MoviesListResponse>,
                response: Response<MoviesListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { movies ->
                        getMoviesCallback.onResponse(movies)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getMovies.${t.message.toString()}")
            }

        })
    }

    fun getMoviesDetail(moviesId: String, getMoviesDetailCallback: GetMoviesDetailCallback) {
        val client = apiConfig.getApiService().getMoviesDetail(moviesId, API)
        client.enqueue(object : Callback<MoviesDetailResponse> {
            override fun onResponse(
                call: Call<MoviesDetailResponse>,
                response: Response<MoviesDetailResponse>
            ) {
                if (response.isSuccessful) {
                    getMoviesDetailCallback.onResponse(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getMoviesDetail.${t.message.toString()}")
            }

        })
    }

    fun getTvSeries(getTvSeriesCallback: GetTvSeriesCallback) {
        val client = apiConfig.getApiService().getTvSeries(API)
        client.enqueue(object : Callback<TvSeriesResponse> {
            override fun onResponse(
                call: Call<TvSeriesResponse>,
                response: Response<TvSeriesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { tvSeries ->
                        getTvSeriesCallback.onResponse(tvSeries)
                    }
                }
            }

            override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getTvSeries.${t.message.toString()}")
            }

        })
    }

    fun getTvSeriesDetail(
        tvSeriesId: String,
        getTvSeriesDetailCallback: GetTvSeriesDetailCallback
    ) {
        val client = apiConfig.getApiService().getTvSeriesDetail(tvSeriesId, API)
        client.enqueue(object : Callback<TvSeriesDetailResponse> {
            override fun onResponse(
                call: Call<TvSeriesDetailResponse>,
                response: Response<TvSeriesDetailResponse>
            ) {
                if (response.isSuccessful) {
                    getTvSeriesDetailCallback.onResponse(response.body()!!)
                }
            }

            override fun onFailure(call: Call<TvSeriesDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getTvSeriesDetail.${t.message.toString()}")
            }

        })
    }

    interface GetMoviesCallback {
        fun onResponse(moviesResponse: List<MoviesListItem>)
    }

    interface GetMoviesDetailCallback {
        fun onResponse(moviesDetailResponse: MoviesDetailResponse)
    }

    interface GetTvSeriesCallback {
        fun onResponse(tvSeriesResponse: List<TvSeriesListItem>)
    }

    interface GetTvSeriesDetailCallback {
        fun onResponse(tvSeriesDetailResponse: TvSeriesDetailResponse)
    }

}