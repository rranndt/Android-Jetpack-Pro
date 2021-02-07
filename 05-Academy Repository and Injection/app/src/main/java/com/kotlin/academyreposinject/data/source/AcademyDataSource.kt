package com.kotlin.academyreposinject.data.source

import com.kotlin.academyreposinject.data.CourseEntity
import com.kotlin.academyreposinject.data.ModuleEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getCoursesWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity

}