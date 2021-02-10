package com.kotlin.academylivedata.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academylivedata.data.source.local.entity.CourseEntity
import com.kotlin.academylivedata.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()

}