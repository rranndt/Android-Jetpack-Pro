package com.kotlin.academyroom.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.source.local.entity.ModuleEntity
import com.kotlin.academyroom.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId = MutableLiveData<String>()
    var moduleId = MutableLiveData<String>()

    fun setCourseId(courseId: String) {
        this.courseId.value = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId.value = moduleId
    }

    var modules: LiveData<Resource<List<ModuleEntity>>> =
        Transformations.switchMap(courseId) { mCourseId ->
            academyRepository.getAllModulesByCourse(mCourseId)
        }

    var selectedModule: LiveData<Resource<ModuleEntity>> =
        Transformations.switchMap(moduleId) { selectedPosition ->
            academyRepository.getContent(selectedPosition)
        }

    fun readContent(modules: ModuleEntity) {
        academyRepository.setReadModule(modules)
    }

    fun getModuleSize(): Int {
        if (modules.value != null) {
            val moduleEntities = modules.value?.data
            if (moduleEntities != null) {
                return moduleEntities.size
            }
        }
        return 0
    }

    fun setNextPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data
            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    moduleId.value = moduleEntities[position + 1].moduleId
                }
            }
        }
    }

    fun setPrevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data
            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    moduleId.value = moduleEntities[position - 1].moduleId
                }
            }
        }
    }

}