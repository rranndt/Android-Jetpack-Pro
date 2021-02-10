package com.kotlin.academylivedata.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academylivedata.data.source.local.entity.CourseEntity
import com.kotlin.academylivedata.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}