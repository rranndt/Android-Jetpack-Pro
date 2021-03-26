package com.kotlin.finalsub.data.source.remote.response.tv.cast

import com.google.gson.annotations.SerializedName

data class TvSeriesCastResponse(

    @field:SerializedName("cast")
    val cast: List<TvSeriesCastItem>,

    @field:SerializedName("id")
    val id: Int

)

