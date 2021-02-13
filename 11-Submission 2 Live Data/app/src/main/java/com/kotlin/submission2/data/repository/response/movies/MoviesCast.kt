package com.kotlin.submission2.data.repository.response.movies

import com.google.gson.annotations.SerializedName

data class MoviesCast(

	@field:SerializedName("cast")
	val cast: List<MoviesCastItem>,

	@field:SerializedName("id")
	val id: Int,

	)

data class MoviesCastItem(

	@field:SerializedName("cast_id")
	val castId: Int,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("id")
	val id: Int,

)