package com.kotlin.academyreposinject.data.source.remote

import com.kotlin.academyreposinject.data.source.remote.response.ContentResponse
import com.kotlin.academyreposinject.data.source.remote.response.CourseResponse
import com.kotlin.academyreposinject.data.source.remote.response.ModuleResponse
import com.kotlin.academyreposinject.utils.JsonHelper

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourse()

    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)

}