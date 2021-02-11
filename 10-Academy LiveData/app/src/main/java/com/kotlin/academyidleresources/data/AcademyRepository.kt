package com.kotlin.academyidleresources.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.academyidleresources.data.source.local.entity.ContentEntity
import com.kotlin.academyidleresources.data.source.local.entity.CourseEntity
import com.kotlin.academyidleresources.data.source.local.entity.ModuleEntity
import com.kotlin.academyidleresources.data.source.remote.RemoteDataSource
import com.kotlin.academyidleresources.data.source.remote.RemoteDataSource.LoadCoursesCallback
import com.kotlin.academyidleresources.data.source.remote.response.ContentResponse
import com.kotlin.academyidleresources.data.source.remote.response.CourseResponse
import com.kotlin.academyidleresources.data.source.remote.response.ModuleResponse
import kotlin.collections.ArrayList

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteData)
            }
    }

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResult.postValue(courseList)
            }
        })
        return courseResult
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru,
    // gabungan antara course dengan module-nya.
    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult = MutableLiveData<CourseEntity>()
        remoteDataSource.getAllCourses(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                lateinit var course: CourseEntity
                for (response in courseResponses) {
                    if (response.id == courseId) {
                        course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                    }
                }
                courseResult.postValue(course)
            }
        })
        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResult = MutableLiveData<List<ModuleEntity>>()
        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponse) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )

                    moduleList.add(course)
                }
                moduleResult.postValue(moduleList)
            }
        })
        return moduleResult
    }


    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()
        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                for (response in moduleResponse) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                        )
                        remoteDataSource.getContent(
                            moduleId,
                            object : RemoteDataSource.LoadContentCallback {
                                override fun onContentReceived(contentResponse: ContentResponse) {
                                    module.contentEntity = ContentEntity(contentResponse.content)
                                    moduleResult.postValue(module)
                                }
                            })
                        break
                    }
                }
            }
        })
        return moduleResult
    }
}