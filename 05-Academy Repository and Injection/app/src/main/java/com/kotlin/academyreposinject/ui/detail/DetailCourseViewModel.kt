package com.kotlin.academyreposinject.ui.detail

import androidx.lifecycle.ViewModel
import com.kotlin.academyreposinject.data.CourseEntity
import com.kotlin.academyreposinject.data.ModuleEntity
import com.kotlin.academyreposinject.utils.DataDummy

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class DetailCourseViewModel : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        lateinit var course: CourseEntity
        val courseEntities = DataDummy.generateDummyCourse()
        for (courseEntity in courseEntities) {
            if (courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }
        return course
    }

    fun getModule(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)

}