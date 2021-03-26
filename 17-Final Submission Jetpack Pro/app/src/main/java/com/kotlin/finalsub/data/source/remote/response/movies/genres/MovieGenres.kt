package com.kotlin.finalsub.data.source.remote.response.movies.genres

import com.google.gson.annotations.SerializedName

data class MovieGenres(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
