package com.kotlin.submission2.data.repository.response.movies

import com.google.gson.annotations.SerializedName

data class MovieGenres(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
