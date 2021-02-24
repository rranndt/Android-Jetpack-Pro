package com.kotlin.academyroom.di

import android.content.Context
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.local.LocalDataSource
import com.kotlin.academyroom.data.source.local.room.AcademyDatabase
import com.kotlin.academyroom.data.source.remote.RemoteDataSource
import com.kotlin.academyroom.utils.AppExecutors
import com.kotlin.academyroom.utils.JsonHelper

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {

    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}