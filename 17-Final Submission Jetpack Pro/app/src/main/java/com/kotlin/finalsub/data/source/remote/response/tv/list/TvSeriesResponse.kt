package com.kotlin.finalsub.data.source.remote.response.tv.list

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(

    @field:SerializedName("results")
    val results: List<TvSeriesListItem>,

    )