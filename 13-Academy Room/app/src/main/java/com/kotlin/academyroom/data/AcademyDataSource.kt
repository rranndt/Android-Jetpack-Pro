package com.kotlin.academyroom.data

import androidx.lifecycle.LiveData
import com.kotlin.academyroom.data.source.local.entity.CourseEntity
import com.kotlin.academyroom.data.source.local.entity.CourseWithModule
import com.kotlin.academyroom.data.source.local.entity.ModuleEntity
import com.kotlin.academyroom.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface AcademyDataSource {

    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)

}