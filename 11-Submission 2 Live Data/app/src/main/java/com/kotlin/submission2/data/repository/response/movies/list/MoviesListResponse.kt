package com.kotlin.submission2.data.repository.response.movies.list

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(

	@field:SerializedName("results")
	val results: List<MoviesListItem>

	)
