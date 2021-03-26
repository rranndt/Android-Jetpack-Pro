package com.kotlin.finalsub.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.finalsub.data.source.local.LocalDataSource
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.ApiResponse
import com.kotlin.finalsub.data.source.remote.RemoteDataSource
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.movies.detail.MoviesDetailItem
import com.kotlin.finalsub.data.source.remote.response.movies.list.MoviesListItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.detail.TvSeriesDetailItem
import com.kotlin.finalsub.data.source.remote.response.tv.list.TvSeriesListItem
import com.kotlin.finalsub.utils.AppExecutors
import com.kotlin.finalsub.utils.Helper
import com.kotlin.finalsub.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueSource {

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    /**
     * MOVIES
     */
    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>,
                List<MoviesListItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MoviesListItem>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<MoviesListItem>) {
                val moviesList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movies = with(response) {
                        MoviesEntity(
                            id,
                            posterPath,
                            "",
                            "",
                            "",
                            "",
                            0,
                            title,
                            0F,
                            voteAverage,
                            false

                        )
                    }
                    moviesList.add(movies)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getMoviesDetail(moviesId: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesDetailItem>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localDataSource.getMoviesById(moviesId)
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return data != null && data.genres == ""
            }

            override fun createCall(): LiveData<ApiResponse<MoviesDetailItem>> {
                return remoteDataSource.getMoviesDetail(moviesId)
            }

            override fun saveCallResult(data: MoviesDetailItem) {
                val genre = Helper.joinGenres(data)
                val moviesDetail = with(data) {
                    MoviesEntity(
                        id,
                        posterPath,
                        backdropPath,
                        genre.toString(),
                        overview,
                        releaseDate,
                        runtime,
                        title,
                        popularity,
                        voteAverage,
                        false
                    )
                }
                localDataSource.updateMovies(moviesDetail, false)
            }

        }.asLiveData()
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

    override fun setMoviesFavorite(movies: MoviesEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateMovies(movies, state)
        }
    }

    override fun getMoviesFavorite(sort: String): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()

        return LivePagedListBuilder(localDataSource.getMoviesFavorite(sort), config).build()
    }

    /**
     * TV SERIES
     */
    override fun getTvSeries(): LiveData<Resource<PagedList<TvSeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<TvSeriesEntity>,
                List<TvSeriesListItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvSeriesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvSeries(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvSeriesEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvSeriesListItem>>> {
                return remoteDataSource.getTvSeries()
            }

            override fun saveCallResult(data: List<TvSeriesListItem>) {
                val tvSeriesList = ArrayList<TvSeriesEntity>()
                for (response in data) {
                    val tvSeries = with(response) {
                        TvSeriesEntity(
                            id,
                            posterPath,
                            "",
                            title,
                            "",
                            "",
                            voteAverage,
                            "",
                            0F,
                            false

                        )
                    }
                    tvSeriesList.add(tvSeries)
                }
                localDataSource.insertTvSeries(tvSeriesList)
            }

        }.asLiveData()
    }

    override fun getTvSeriesDetail(tvSeriesId: String): LiveData<Resource<TvSeriesEntity>> {
        return object : NetworkBoundResource<TvSeriesEntity, TvSeriesDetailItem>(appExecutors) {
            override fun loadFromDB(): LiveData<TvSeriesEntity> {
                return localDataSource.getTvSeriesById(tvSeriesId)
            }

            override fun shouldFetch(data: TvSeriesEntity?): Boolean {
                return data?.genres.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvSeriesDetailItem>> {
                return remoteDataSource.getTvSeriesDetail(tvSeriesId)
            }

            override fun saveCallResult(data: TvSeriesDetailItem) {
                val genre = Helper.joinGenres(data)
                val tvSeriesDetail = with(data) {
                    TvSeriesEntity(
                        id,
                        posterPath,
                        backdropPath,
                        originalName,
                        genre.toString(),
                        firstAirDate,
                        voteAverage,
                        overview,
                        popularity,
                        false
                    )
                }
                localDataSource.updateTvSeries(tvSeriesDetail, false)
            }
        }.asLiveData()
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

    override fun setTvSeriesFavorite(tvSeries: TvSeriesEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateTvSeries(tvSeries, state)
        }
    }

    override fun getTvSeriesFavorite(sort: String): LiveData<PagedList<TvSeriesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()

        return LivePagedListBuilder(localDataSource.getTvSeriesFavorite(sort), config).build()

    }

}