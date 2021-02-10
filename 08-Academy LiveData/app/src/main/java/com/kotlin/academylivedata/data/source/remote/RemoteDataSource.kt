package com.kotlin.academylivedata.data.source.remote

import android.os.Handler
import android.os.Looper
import com.kotlin.academylivedata.data.source.remote.response.ContentResponse
import com.kotlin.academylivedata.data.source.remote.response.CourseResponse
import com.kotlin.academylivedata.data.source.remote.response.ModuleResponse
import com.kotlin.academylivedata.utils.JsonHelper
import org.json.JSONArray

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourse())
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponse: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }

}