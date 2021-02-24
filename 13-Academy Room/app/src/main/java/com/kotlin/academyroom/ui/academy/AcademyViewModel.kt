package com.kotlin.academyroom.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.local.entity.CourseEntity
import com.kotlin.academyroom.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()

}