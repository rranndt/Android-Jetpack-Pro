package com.kotlin.submission2.data.repository.response.movies.list

import com.google.gson.annotations.SerializedName

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
data class MoviesListItem(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("id")
    val id: Int,

    )