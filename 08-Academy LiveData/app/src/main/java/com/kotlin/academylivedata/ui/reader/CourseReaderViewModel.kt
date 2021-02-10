package com.kotlin.academylivedata.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.academylivedata.data.source.local.entity.ModuleEntity
import com.kotlin.academylivedata.data.AcademyRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)

}