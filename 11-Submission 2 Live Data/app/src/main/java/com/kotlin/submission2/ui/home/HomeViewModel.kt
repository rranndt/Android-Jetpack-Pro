package com.kotlin.submission2.ui.home

import androidx.lifecycle.ViewModel
import com.kotlin.submission2.model.DataEntity
import com.kotlin.submission2.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModel : ViewModel() {

    fun getListMovies(): List<DataEntity> = DataDummy.generateDummyMovies()

    fun getListTvSeries(): List<DataEntity> = DataDummy.generateDummyTvSeries()

}