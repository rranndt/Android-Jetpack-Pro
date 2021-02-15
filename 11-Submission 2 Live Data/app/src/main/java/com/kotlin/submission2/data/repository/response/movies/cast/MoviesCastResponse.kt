package com.kotlin.submission2.data.repository.response.movies.cast

import com.google.gson.annotations.SerializedName

data class MoviesCastResponse(

	@field:SerializedName("cast")
	val cast: List<MoviesCastItem>,

	@field:SerializedName("id")
	val id: Int

	)