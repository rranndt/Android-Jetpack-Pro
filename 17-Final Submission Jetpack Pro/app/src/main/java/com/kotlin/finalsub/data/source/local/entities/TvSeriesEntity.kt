package com.kotlin.finalsub.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.finalsub.utils.Constant.Companion.TV_SERIES_TABLE

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Entity(tableName = TV_SERIES_TABLE)
data class TvSeriesEntity(

    @PrimaryKey
    val id: Int,

    val posterPath: String,

    val backdropPath: String,

    val title: String,

    val genres: String,

    val releaseData: String,

    val voteAverage: Float,

    val overview: String,

    val popularity: Float,

    var isFavorite: Boolean = false

)