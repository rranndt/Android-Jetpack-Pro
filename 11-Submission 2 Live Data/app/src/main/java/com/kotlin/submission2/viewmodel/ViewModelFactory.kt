package com.kotlin.submission2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.submission2.data.repository.DataRepository
import com.kotlin.submission2.di.Injection
import com.kotlin.submission2.ui.MainViewModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepository: DataRepository) :
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
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                dataRepository
            ) as T
            else -> throw IllegalArgumentException("Unknow ViewModel: " + modelClass.name)
        }
    }
}