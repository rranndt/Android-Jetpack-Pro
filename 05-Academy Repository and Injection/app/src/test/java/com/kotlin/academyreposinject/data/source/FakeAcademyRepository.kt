package com.kotlin.academyreposinject.data.source

import com.kotlin.academyreposinject.data.AcademyDataSource
import com.kotlin.academyreposinject.data.source.local.entity.ContentEntity
import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.source.local.entity.ModuleEntity
import com.kotlin.academyreposinject.data.source.remote.RemoteDataSource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) :
    AcademyDataSource {

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

    override fun getCourseWithModules(courseId: String): CourseEntity {
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
                response.courseId,
                response.moduleId,
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