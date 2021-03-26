package com.kotlin.finalsub.data.source.remote.response.tv.genres

import com.google.gson.annotations.SerializedName

data class TvSeriesGenres(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
