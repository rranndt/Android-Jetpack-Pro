package com.kotlin.academies.di

import android.content.Context

import com.kotlin.academies.data.AcademyRepository
import com.kotlin.academies.data.source.local.LocalDataSource
import com.kotlin.academies.data.source.local.room.AcademyDatabase
import com.kotlin.academies.data.source.remote.RemoteDataSource
import com.kotlin.academies.utils.AppExecutors
import com.kotlin.academies.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
