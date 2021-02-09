package com.kotlin.academyreposinject.ui.bookmark

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()

}