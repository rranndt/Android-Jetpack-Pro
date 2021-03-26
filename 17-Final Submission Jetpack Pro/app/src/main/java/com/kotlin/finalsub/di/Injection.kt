package com.kotlin.finalsub.di

import android.content.Context
import com.kotlin.finalsub.data.Repository
import com.kotlin.finalsub.data.source.local.LocalDataSource
import com.kotlin.finalsub.data.source.local.room.CatalogueDatabase
import com.kotlin.finalsub.data.source.remote.RemoteDataSource
import com.kotlin.finalsub.network.ApiConfig
import com.kotlin.finalsub.utils.AppExecutors

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {

    fun provideRepository(context: Context): Repository {
        val database = CatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}