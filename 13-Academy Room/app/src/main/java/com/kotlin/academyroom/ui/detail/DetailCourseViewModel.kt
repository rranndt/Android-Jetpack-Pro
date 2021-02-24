package com.kotlin.academyroom.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.local.entity.CourseEntity
import com.kotlin.academyroom.data.source.local.entity.ModuleEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModule(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

}