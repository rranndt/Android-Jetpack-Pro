package com.kotlin.finalsub.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import com.kotlin.finalsub.utils.Constant.Companion.NEWEST
import com.kotlin.finalsub.utils.Constant.Companion.OLDEST
import com.kotlin.finalsub.utils.Constant.Companion.RATING
import com.kotlin.finalsub.utils.Constant.Companion.TITLE

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object SortUtils {

    fun getMoviesSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movies_table WHERE isFavorite = 1 ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title")
            RATING -> simpleQuery.append("ORDER BY voteAverage DESC")
            NEWEST -> simpleQuery.append("ORDER BY releaseDate DESC")
            OLDEST -> simpleQuery.append("ORDER BY releaseDate ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getTvSeriesSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tv_series_table WHERE isFavorite = 1 ")
        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title")
            RATING -> simpleQuery.append("ORDER BY voteAverage DESC")
            NEWEST -> simpleQuery.append("ORDER BY releaseData DESC")
            OLDEST -> simpleQuery.append("ORDER BY releaseData ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}