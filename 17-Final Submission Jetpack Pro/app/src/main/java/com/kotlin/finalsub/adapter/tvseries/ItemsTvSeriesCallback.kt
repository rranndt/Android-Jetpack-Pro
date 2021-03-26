package com.kotlin.finalsub.adapter.tvseries

import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface ItemsTvSeriesCallback {
    fun onItemTvSeriesClicked(tvSeries: TvSeriesEntity)
}