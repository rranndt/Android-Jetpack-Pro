package com.kotlin.academylivedata.di

import android.content.Context
import com.kotlin.academylivedata.data.AcademyRepository
import com.kotlin.academylivedata.data.source.remote.RemoteDataSource
import com.kotlin.academylivedata.utils.JsonHelper

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