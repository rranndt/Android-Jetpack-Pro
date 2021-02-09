package com.kotlin.academyreposinject.data

import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.source.local.entity.ModuleEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity

}