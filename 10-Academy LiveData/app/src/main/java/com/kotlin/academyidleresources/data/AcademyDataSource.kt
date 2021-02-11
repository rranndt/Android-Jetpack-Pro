package com.kotlin.academyidleresources.data

import androidx.lifecycle.LiveData
import com.kotlin.academyidleresources.data.source.local.entity.CourseEntity
import com.kotlin.academyidleresources.data.source.local.entity.ModuleEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface AcademyDataSource {

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>

}