package com.kotlin.submission2.data.repository.response.movies

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@field:SerializedName("results")
	val results: List<MoviesListItem>,

	)

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
