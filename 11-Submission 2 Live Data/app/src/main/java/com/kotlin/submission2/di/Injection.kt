package com.kotlin.submission2.di

import com.kotlin.submission2.data.repository.DataRepository
import com.kotlin.submission2.data.repository.remote.RemoteDataSource
import com.kotlin.submission2.network.ApiConfig

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {

    fun provideRepository(): DataRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        return DataRepository.getInstance(remoteDataSource)
    }

}