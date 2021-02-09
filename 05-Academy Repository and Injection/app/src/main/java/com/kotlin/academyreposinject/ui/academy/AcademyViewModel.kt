package com.kotlin.academyreposinject.ui.academy

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()

}