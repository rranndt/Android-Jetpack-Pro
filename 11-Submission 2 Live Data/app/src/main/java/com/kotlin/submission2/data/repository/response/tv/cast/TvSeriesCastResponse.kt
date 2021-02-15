package com.kotlin.submission2.data.repository.response.tv.cast

import com.google.gson.annotations.SerializedName

data class TvSeriesCastResponse(

    @field:SerializedName("cast")
    val cast: List<TvSeriesCastItem>,

    @field:SerializedName("id")
    val id: Int

)

