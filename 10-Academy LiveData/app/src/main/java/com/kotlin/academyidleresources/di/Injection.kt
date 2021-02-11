package com.kotlin.academyidleresources.di

import android.content.Context
import com.kotlin.academyidleresources.data.AcademyRepository
import com.kotlin.academyidleresources.data.source.remote.RemoteDataSource
import com.kotlin.academyidleresources.utils.JsonHelper

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