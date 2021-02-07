package com.kotlin.academyreposinject.ui.academy

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.CourseEntity
import com.kotlin.academyreposinject.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourse()

}