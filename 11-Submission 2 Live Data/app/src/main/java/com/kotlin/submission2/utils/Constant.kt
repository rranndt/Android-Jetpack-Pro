package com.kotlin.submission2.utils

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Constant {

    // Parcelable
    const val BUNDLE1 = "bundle1"
    const val BUNDLE2 = "bundle2"
    const val BUNDLE_MOVIES = "bundle_movies"
    const val BUNDLE_TV_SERIES = "bundle_tvseries"

    // Chart
    const val MAX_PROGRESS_CHART =  10f
    const val START_ANGLE_PROGRESS_CHART = 0f

    // Api Key
    const val API = "02a392537fe5d151a7e58efe5f2e1838"

    // Url
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val MOVIES_POPULAR = "movie/popular"
    const val MOVIES_WITH_ID = "movie/{movie_id}"
    const val MOVIES_CAST = "movie/{movie_id}/credits"
    const val TV_SERIES_POPULAR = "tv/popular"
    const val TV_SERIES_WITH_ID = "tv/{tv_series_id}"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

    // Api Client
    const val STRING_API_KEY = "api_key"
    const val STRING_MOVIES_ID = "movie_id"
    const val STRING_MOVIES_CAST_ID = "movie_id"
    const val STRING_TV_SERIES_ID = "tv_series_id"

    // Date
    const val DATE_CURRENT_FORMAT = "yyy-MM-dd"
    const val DATE_REQUIRED_FORMAT = "yyyy"

    // Slider
    var NUM_PAGES = 0
    var CURRENT_PAGES = 0

}