package com.kotlin.academies.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.academies.data.AcademyRepository
import com.kotlin.academies.data.source.local.entity.CourseEntity
import com.kotlin.academies.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> =
            academyRepository.getAllCourses()
}

