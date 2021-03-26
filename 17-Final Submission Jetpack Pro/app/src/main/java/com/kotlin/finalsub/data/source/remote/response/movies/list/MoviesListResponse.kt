package com.kotlin.finalsub.data.source.remote.response.movies.list

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(

	@field:SerializedName("results")
	val results: List<MoviesListItem>

	)
