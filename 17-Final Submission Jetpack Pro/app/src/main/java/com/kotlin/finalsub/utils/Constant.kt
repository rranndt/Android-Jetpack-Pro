package com.kotlin.finalsub.utils

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class Constant {

    companion object {

        // Parcelable
        const val BUNDLE1 = "bundle1"
        const val BUNDLE2 = "bundle2"
        const val BUNDLE_MOVIES = "bundle_movies"
        const val BUNDLE_TV_SERIES = "bundle_tvseries"

        // Chart
        const val MAX_PROGRESS_CHART = 10f
        const val START_ANGLE_PROGRESS_CHART = 0f

        // Url
        const val MOVIES_POPULAR = "movie/popular"
        const val MOVIES_WITH_ID = "movie/{movie_id}"
        const val MOVIES_CAST = "movie/{movie_id}/credits"
        const val TV_SERIES_POPULAR = "tv/popular"
        const val TV_SERIES_WITH_ID = "tv/{tv_series_id}"
        const val TV_SERIES_CAST = "tv/{tv_id}/credits"

        // Api Client
        const val STRING_API_KEY = "api_key"
        const val STRING_MOVIES_ID = "movie_id"
        const val STRING_MOVIES_CAST_ID = "movie_id"
        const val STRING_TV_SERIES_ID = "tv_series_id"
        const val STRING_TV_SERIES_CAST_ID = "tv_id"

        // Date
        const val DATE_CURRENT_FORMAT = "yyyy-MM-dd"
        const val DATE_REQUIRED_FORMAT = "yyyy"

        // Room Database
        const val MOVIES_TABLE = "movies_table"
        const val TV_SERIES_TABLE = "tv_series_table"
        const val DATABASE_NAME = "cinema_catalogue.db"

        // Sort
        const val TITLE = "title"
        const val RATING = "rating"
        const val NEWEST = "newest"
        const val OLDEST = "oldest"

        // Preference
        const val PREFS_SORT_MOVIES_BY_TITLE = "sort_movies_by_title"
        const val PREFS_SORT_MOVIES_BY_RATING = "sort_movies_by_rating"
        const val PREFS_SORT_MOVIES_BY_NEWEST = "sort_movies_by_newest"
        const val PREFS_SORT_MOVIES_BY_OLDEST = "sort_movies_by_oldest"
        const val PREFS_SORT_TV_SERIES_BY_TITLE = "sort_tv_series_by_title"
        const val PREFS_SORT_TV_SERIES_BY_RATING = "sort_tv_series_by_rating"
        const val PREFS_SORT_TV_SERIES_BY_NEWEST = "sort_tv_series_by_newest"
        const val PREFS_SORT_TV_SERIES_BY_OLDEST = "sort_tv_series_by_oldest"

    }
}