package com.kotlin.finalsub.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.finalsub.BuildConfig.API_KEY
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastResponse
import com.kotlin.finalsub.data.source.remote.response.movies.detail.MoviesDetailItem
import com.kotlin.finalsub.data.source.remote.response.movies.list.MoviesListItem
import com.kotlin.finalsub.data.source.remote.response.movies.list.MoviesListResponse
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastResponse
import com.kotlin.finalsub.data.source.remote.response.tv.detail.TvSeriesDetailItem
import com.kotlin.finalsub.data.source.remote.response.tv.list.TvSeriesListItem
import com.kotlin.finalsub.data.source.remote.response.tv.list.TvSeriesResponse
import com.kotlin.finalsub.network.ApiConfig
import com.kotlin.finalsub.utils.EspressoIdlingResource.decrement
import com.kotlin.finalsub.utils.EspressoIdlingResource.increment
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

    /**
     * MOVIES
     */
    fun getMovies(): LiveData<ApiResponse<List<MoviesListItem>>> {
        increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesListItem>>>()
        val client = apiConfig.getApiService().getMovies(API_KEY)
        client.enqueue(object : Callback<MoviesListResponse> {
            override fun onResponse(
                call: Call<MoviesListResponse>,
                response: Response<MoviesListResponse>
            ) {
                if (response.isSuccessful) {
                    resultMovies.postValue(
                        ApiResponse.success(response.body()?.results as List<MoviesListItem>)
                    )
                } else {
                    ApiResponse.empty(response.message(), response.body())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "onFailure: getMovies.${t.message.toString()}")
                decrement()
            }

        })
        return resultMovies
    }

    fun getMoviesDetail(moviesId: String): MutableLiveData<ApiResponse<MoviesDetailItem>> {
        increment()
        val resultDetailMovies = MutableLiveData<ApiResponse<MoviesDetailItem>>()
        val client = apiConfig.getApiService().getMoviesDetail(moviesId, API_KEY)
        client.enqueue(object : Callback<MoviesDetailItem> {
            override fun onResponse(
                call: Call<MoviesDetailItem>,
                response: Response<MoviesDetailItem>
            ) {
                if (response.isSuccessful) {
                    resultDetailMovies.postValue(
                        ApiResponse.success(response.body() as MoviesDetailItem)
                    )
                } else {
                    ApiResponse.empty(response.message(), response.errorBody())
                }
                decrement()
            }

            override fun onFailure(call: Call<MoviesDetailItem>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "onFailure: getMoviesDetail.${t.message.toString()}")
                decrement()
            }

        })
        return resultDetailMovies
    }

    fun getMoviesCast(moviesId: String, getMoviesCastCallback: GetMoviesCastCallback) {
        increment()
        val client = apiConfig.getApiService().getMoviesCast(moviesId, API_KEY)
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
                decrement()
            }

            override fun onFailure(call: Call<MoviesCastResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: getMoviesCast.${t.message.toString()}")
            }

        })
    }

    /**
     * TV SERIES
     */
    fun getTvSeries(): LiveData<ApiResponse<List<TvSeriesListItem>>> {
        increment()
        val resultTvSeries = MutableLiveData<ApiResponse<List<TvSeriesListItem>>>()
        val client = apiConfig.getApiService().getTvSeries(API_KEY)
        client.enqueue(object : Callback<TvSeriesResponse> {
            override fun onResponse(
                call: Call<TvSeriesResponse>,
                response: Response<TvSeriesResponse>
            ) {
                if (response.isSuccessful) {
                    resultTvSeries.postValue(
                        ApiResponse.success(response.body()?.results as List<TvSeriesListItem>)
                    )
                } else {
                    ApiResponse.empty(response.message(), response.body())
                    Log.e(TAG, response.message())
                }
                decrement()
            }

            override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "onFailure: getTvSeries.${t.message.toString()}")
                decrement()
            }
        })
        return resultTvSeries
    }

    fun getTvSeriesDetail(
        tvSeriesId: String
    ): LiveData<ApiResponse<TvSeriesDetailItem>> {
        increment()
        val resultTvSeriesDetail = MutableLiveData<ApiResponse<TvSeriesDetailItem>>()
        val client = apiConfig.getApiService().getTvSeriesDetail(tvSeriesId, API_KEY)
        client.enqueue(object : Callback<TvSeriesDetailItem> {
            override fun onResponse(
                call: Call<TvSeriesDetailItem>,
                response: Response<TvSeriesDetailItem>
            ) {
                if (response.isSuccessful) {
                    resultTvSeriesDetail.postValue(
                        ApiResponse.success(response.body() as TvSeriesDetailItem)
                    )
                } else {
                    ApiResponse.empty(response.message(), response.errorBody())
                }
                decrement()
            }

            override fun onFailure(call: Call<TvSeriesDetailItem>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "onFailure: getTvSeriesDetail.${t.message.toString()}")
                decrement()
            }
        })
        return resultTvSeriesDetail
    }

    fun getTvSeriesCast(tvSeriesId: String, getTvSeriesCastCallback: GetTvSeriesCastCallback) {
        increment()
        val client = apiConfig.getApiService().getTvSeriesCast(tvSeriesId, API_KEY)
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
                decrement()
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