package com.kotlin.academyreposinject.ui.bookmark

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.CourseEntity
import com.kotlin.academyreposinject.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourse()

}