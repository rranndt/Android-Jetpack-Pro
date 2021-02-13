package com.kotlin.submission2.data.repository.response.tv

import com.google.gson.annotations.SerializedName

data class TvSeriesGenres(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
