package com.kotlin.academyidleresources.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academyidleresources.data.source.local.entity.CourseEntity
import com.kotlin.academyidleresources.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}