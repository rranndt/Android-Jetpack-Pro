package com.kotlin.academyreposinject.di

import android.content.Context
import com.kotlin.academyreposinject.data.AcademyRepository
import com.kotlin.academyreposinject.data.source.remote.RemoteDataSource
import com.kotlin.academyreposinject.utils.JsonHelper

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