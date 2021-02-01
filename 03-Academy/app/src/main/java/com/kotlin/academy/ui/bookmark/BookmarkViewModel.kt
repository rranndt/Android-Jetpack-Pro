package com.kotlin.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.kotlin.academy.data.CourseEntity
import com.kotlin.academy.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourse()

}