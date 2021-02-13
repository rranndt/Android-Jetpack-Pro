package com.kotlin.submission2.di

import com.kotlin.submission2.data.repository.MoviesRepository
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.network.ApiConfig

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {

    fun provideRepository(): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        return MoviesRepository.getInstance(remoteDataSource)!!
    }

}