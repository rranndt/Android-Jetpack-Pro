package com.kotlin.submission2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.submission2.data.repository.MoviesRepository
import com.kotlin.submission2.di.Injection
import com.kotlin.submission2.ui.home.HomeViewModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class ViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                moviesRepository
            ) as T
            else -> throw IllegalArgumentException("Unknow ViewModel: " + modelClass.name)
        }
    }
}