package com.kotlin.submission2.data.repository.remote

import android.util.Log
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastResponse
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListResponse
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastResponse
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesResponse
import com.kotlin.submission2.network.ApiConfig
import com.kotlin.submission2.utils.Constant.API
import com.kotlin.submission2.utils.EspressoIdlingResource.decrement
import com.kotlin.submission2.utils.EspressoIdlingResource.increment
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
        increment()
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
                        decrement()
                    }
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: getMovies.${t.message.toString()}")
                }

            })
    }

    fun getMoviesDetail(moviesId: String, getMoviesDetailCallback: GetMoviesDetailCallback) {
        increment()
        val client = apiConfig.getApiService().getMoviesDetail(moviesId, API)
        client.enqueue(object : Callback<MoviesDetailItem> {
            override fun onResponse(
                call: Call<MoviesDetailItem>,
                item: Response<MoviesDetailItem>
            ) {
                if (item.isSuccessful) {
                    getMoviesDetailCallback.onResponse(item.body()!!)
                }
                decrement()
            }

            override fun onFailure(call: Call<MoviesDetailItem>, t: Throwable) {
                Log.e(TAG, "onFailure: getMoviesDetail.${t.message.toString()}")
            }

        })
    }

    fun getMoviesCast(moviesId: String, getMoviesCastCallback: GetMoviesCastCallback) {
        val client = apiConfig.getApiService().getMoviesCast(moviesId, API)
        client.enqueue(object : Callback<MoviesCastResponse> {
            override fun onResponse(
                call: Call<MoviesCastResponse>,
                response: Response<MoviesCastResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.cast?.let {
                        getMoviesCastCallback.onResponse(it)
                    }
                }
            }

            override fun onFailure(call: Call<MoviesCastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getMoviesCast.${t.message.toString()}")
            }

        })
    }

    fun getTvSeries(getTvSeriesCallback: GetTvSeriesCallback) {
        increment()
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
                decrement()
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
        increment()
        val client = apiConfig.getApiService().getTvSeriesDetail(tvSeriesId, API)
        client.enqueue(object : Callback<TvSeriesDetailItem> {
            override fun onResponse(
                call: Call<TvSeriesDetailItem>,
                item: Response<TvSeriesDetailItem>
            ) {
                if (item.isSuccessful) {
                    getTvSeriesDetailCallback.onResponse(item.body()!!)
                }
                decrement()
            }

            override fun onFailure(call: Call<TvSeriesDetailItem>, t: Throwable) {
                Log.e(TAG, "onFailure: getTvSeriesDetail.${t.message.toString()}")
            }

        })
    }

    fun getTvSeriesCast(tvSeriesId: String, getTvSeriesCastCallback: GetTvSeriesCastCallback) {
        val client = apiConfig.getApiService().getTvSeriesCast(tvSeriesId, API)
        client.enqueue(object : Callback<TvSeriesCastResponse> {
            override fun onResponse(
                call: Call<TvSeriesCastResponse>,
                response: Response<TvSeriesCastResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.cast?.let {
                        getTvSeriesCastCallback.onResponse(it)
                    }
                }
            }

            override fun onFailure(call: Call<TvSeriesCastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getTvSeriesCast${t.printStackTrace()}")
            }

        })
    }

    interface GetMoviesCallback {
        fun onResponse(moviesResponse: List<MoviesListItem>)
    }

    interface GetMoviesDetailCallback {
        fun onResponse(moviesDetailItem: MoviesDetailItem)
    }

    interface GetMoviesCastCallback {
        fun onResponse(moviesCast: List<MoviesCastItem>)
    }

    interface GetTvSeriesCallback {
        fun onResponse(tvSeriesResponse: List<TvSeriesListItem>)
    }

    interface GetTvSeriesDetailCallback {
        fun onResponse(tvSeriesDetailItem: TvSeriesDetailItem)
    }

    interface GetTvSeriesCastCallback {
        fun onResponse(tvSeriesCast: List<TvSeriesCastItem>)
    }

}