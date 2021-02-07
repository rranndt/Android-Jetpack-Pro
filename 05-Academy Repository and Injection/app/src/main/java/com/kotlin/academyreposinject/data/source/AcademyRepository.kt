package com.kotlin.academyreposinject.data.source

import com.kotlin.academyreposinject.data.ContentEntity
import com.kotlin.academyreposinject.data.CourseEntity
import com.kotlin.academyreposinject.data.ModuleEntity
import com.kotlin.academyreposinject.data.source.remote.RemoteDataSource

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

    override fun getAllCourses(): List<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
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

        return courseList
    }

    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
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

        return courseList
    }

    override fun getCoursesWithModules(courseId: String): CourseEntity {
        val courseResponses = remoteDataSource.getAllCourses()
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

        return course
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for (response in moduleResponses) {
            val course = ModuleEntity(
                response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false
            )

            moduleList.add(course)
        }

        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module: ModuleEntity
        for (response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(
                    response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false
                )

                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }

        return module
    }
}