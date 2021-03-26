package com.kotlin.finalsub.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.finalsub.utils.Constant.Companion.MOVIES_TABLE

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Entity(tableName = MOVIES_TABLE)
data class MoviesEntity(

    @PrimaryKey
    val id: Int,

    val posterPath: String,

    val backdropPath: String,

    val genres: String,

    val overview: String,

    val releaseDate: String,

    val runtime: Int,

    val title: String,

    val popularity: Float,

    val voteAverage: Float,

    var isFavorite: Boolean = false

)