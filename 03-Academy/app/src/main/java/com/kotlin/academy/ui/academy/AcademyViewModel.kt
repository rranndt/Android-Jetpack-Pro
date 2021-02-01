package com.kotlin.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.kotlin.academy.data.CourseEntity
import com.kotlin.academy.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourse()

}