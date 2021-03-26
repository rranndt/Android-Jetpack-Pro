package com.kotlin.finalsub.data.source.remote.response.tv.list

import com.google.gson.annotations.SerializedName

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
data class TvSeriesListItem(


    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val title: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("id")
    val id: Int,

    )