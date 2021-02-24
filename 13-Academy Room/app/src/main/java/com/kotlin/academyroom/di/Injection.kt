package com.kotlin.academyroom.di

import android.content.Context
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.remote.RemoteDataSource
import com.kotlin.academyroom.utils.JsonHelper

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {

    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }

}