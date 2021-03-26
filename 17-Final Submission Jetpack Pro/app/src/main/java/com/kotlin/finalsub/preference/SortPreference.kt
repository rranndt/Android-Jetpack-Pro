package com.kotlin.finalsub.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_MOVIES_BY_NEWEST
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_MOVIES_BY_OLDEST
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_MOVIES_BY_RATING
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_MOVIES_BY_TITLE
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_TV_SERIES_BY_NEWEST
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_TV_SERIES_BY_OLDEST
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_TV_SERIES_BY_RATING
import com.kotlin.finalsub.utils.Constant.Companion.PREFS_SORT_TV_SERIES_BY_TITLE

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
internal class SortPreference {

    private lateinit var preferences: SharedPreferences

    /**
     * Sort Movies By Title
     */
    fun setSortMoviesByTitle(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_TITLE, true) }
    }

    fun getSortMoviesByTitle(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_TITLE, true)
    }

    fun clearSortMoviesByTitle(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_TITLE, false) }
    }

    /**
     * Sort Movies By Rating
     */
    fun setSortMoviesByRating(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_RATING, true) }
    }

    fun getSortMoviesByRating(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_RATING, true)
    }

    fun clearSortMoviesByRating(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_RATING, false) }
    }

    /**
     * Sort Movies By NEWEST
     */
    fun setSortMoviesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_NEWEST, true) }
    }

    fun getSortMoviesByNewest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_NEWEST, true)
    }

    fun clearSortMoviesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_NEWEST, false) }
    }

    /**
     * Sort Movies By Oldest
     */
    fun setSortMoviesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_OLDEST, true) }
    }

    fun getSortMoviesByOldest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_MOVIES_BY_OLDEST, true)
    }

    fun clearSortMoviesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_MOVIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_MOVIES_BY_OLDEST, false) }
    }

    /**
     * Sort Tv Series By Title
     */
    fun setSortTvSeriesByTitle(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_TITLE, true) }
    }

    fun getSortTvSeriesByTitle(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SERIES_BY_TITLE, true)
    }

    fun clearSortTvSeriesByTitle(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_TITLE, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_TITLE, false) }
    }

    /**
     * Sort Tv Series By Rating
     */
    fun setSortTvSeriesByRating(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_RATING, true) }
    }

    fun getSortTvSeriesByRating(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SERIES_BY_RATING, true)
    }

    fun clearSortTvSeriesByRating(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_RATING, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_RATING, false) }
    }

    /**
     * Sort Tv Series By NEWEST
     */
    fun setSortTvSeriesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_NEWEST, true) }
    }

    fun getSortTvSeriesByNewest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SERIES_BY_NEWEST, true)
    }

    fun clearSortTvSeriesByNewest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_NEWEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_NEWEST, false) }
    }

    /**
     * Sort Tv Series By Oldest
     */
    fun setSortTvSeriesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_OLDEST, true) }
    }

    fun getSortTvSeriesByOldest(context: Context): Boolean {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        return preferences.getBoolean(PREFS_SORT_TV_SERIES_BY_OLDEST, true)
    }

    fun clearSortTvSeriesByOldest(context: Context) {
        preferences = context.getSharedPreferences(
            PREFS_SORT_TV_SERIES_BY_OLDEST, Context.MODE_PRIVATE
        ) as SharedPreferences
        preferences.edit { putBoolean(PREFS_SORT_TV_SERIES_BY_OLDEST, false) }
    }

}