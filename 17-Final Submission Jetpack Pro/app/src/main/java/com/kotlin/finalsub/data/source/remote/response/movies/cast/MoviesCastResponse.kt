package com.kotlin.finalsub.data.source.remote.response.movies.cast

import com.google.gson.annotations.SerializedName

data class MoviesCastResponse(

	@field:SerializedName("cast")
	val cast: List<MoviesCastItem>,

	@field:SerializedName("id")
	val id: Int

	)