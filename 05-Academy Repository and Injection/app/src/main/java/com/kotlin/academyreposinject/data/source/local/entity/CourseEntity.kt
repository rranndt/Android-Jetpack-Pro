package com.kotlin.academyreposinject.data.source.local.entity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
data class CourseEntity(

    var courseId: String,

    var title: String,

    var description: String,

    var deadline: String,

    var bookmarked: Boolean = false,

    var imagePath: String

)
