package com.kotlin.academyreposinject.ui.detail

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.source.local.entity.ModuleEntity
import com.kotlin.academyreposinject.data.AcademyRepository
import com.kotlin.academyreposinject.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModule(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)

}