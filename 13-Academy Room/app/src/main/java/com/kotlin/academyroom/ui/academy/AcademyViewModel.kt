package com.kotlin.academyroom.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.local.entity.CourseEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}