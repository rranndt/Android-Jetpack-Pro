package com.kotlin.academyroom.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
data class CourseWithModule(

    @Embedded
    var mCourse: CourseEntity,

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModule: List<ModuleEntity>

)
