package com.kotlin.submission1.ui.home

import androidx.lifecycle.ViewModel
import com.kotlin.submission1.model.DataEntity
import com.kotlin.submission1.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class HomeViewModel : ViewModel() {

    fun getListMovies(): List<DataEntity> = DataDummy.generateDummyMovies()

    fun getListTvSeries(): List<DataEntity> = DataDummy.generateDummyTvSeries()

}