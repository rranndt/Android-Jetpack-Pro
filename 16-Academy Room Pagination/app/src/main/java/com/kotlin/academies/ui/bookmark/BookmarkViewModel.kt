package com.kotlin.academies.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academies.data.AcademyRepository
import com.kotlin.academies.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> {
        return academyRepository.getBookmarkedCourses()
    }
}

