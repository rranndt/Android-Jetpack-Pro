package com.kotlin.academyroom.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.academyroom.data.AcademyDataSource
import com.kotlin.academyroom.data.AcademyRepository
import com.kotlin.academyroom.data.NetworkBoundResource
import com.kotlin.academyroom.data.source.local.LocalDataSource
import com.kotlin.academyroom.data.source.local.entity.ContentEntity
import com.kotlin.academyroom.data.source.local.entity.CourseEntity
import com.kotlin.academyroom.data.source.local.entity.CourseWithModule
import com.kotlin.academyroom.data.source.local.entity.ModuleEntity
import com.kotlin.academyroom.data.source.remote.ApiResponse
import com.kotlin.academyroom.data.source.remote.RemoteDataSource
import com.kotlin.academyroom.data.source.remote.response.ContentResponse
import com.kotlin.academyroom.data.source.remote.response.CourseResponse
import com.kotlin.academyroom.data.source.remote.response.ModuleResponse
import com.kotlin.academyroom.utils.AppExecutors
import com.kotlin.academyroom.vo.Resource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class FakeAcademyRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AcademyDataSource {

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object :
            NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<CourseEntity>> {
                return localDataSource.getAllCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteDataSource.getAllCourses()
            }

            override fun saveCallResult(data: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in data) {
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
                localDataSource.insertCourses(courseList)
            }

        }.asLiveData()

    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        return localDataSource.getBookmarkedCourses()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.setCourseBookmark(course, state) }
    }

    override fun setReadModule(module: ModuleEntity) {
        return appExecutors.diskIO().execute { localDataSource.setReadModule(module) }
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<CourseWithModule> {
                return localDataSource.getCourseWithModules(courseId)
            }

            override fun shouldFetch(data: CourseWithModule?): Boolean {
                return data?.mModule == null || data.mModule.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object :
            NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<ModuleEntity>> {
                return localDataSource.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<ModuleEntity> {
                return localDataSource.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(data: ModuleEntity?): Boolean {
                return data?.contentEntity == null
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                return remoteDataSource.getContent(moduleId)
            }

            override fun saveCallResult(data: ContentResponse) {
                return localDataSource.updateContent(data.content.toString(), moduleId)
            }

        }.asLiveData()
    }
}