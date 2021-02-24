package com.kotlin.academyroom.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Entity(
    tableName = "moduleentities",
    primaryKeys = ["moduleId", "courseId"],
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = ["courseId"],
        childColumns = ["courseId"]
    )],
    indices = [Index(value = ["moduleId"]),
        Index(value = ["courseId"])]
)
data class ModuleEntity(

    @NonNull
    @ColumnInfo(name = "moduleId")
    var moduleId: String,

    @NonNull
    @ColumnInfo(name = "courseId")
    var courseId: String,

    @NonNull
    @ColumnInfo(name = "title")
    var title: String,

    @NonNull
    @ColumnInfo(name = "position")
    var position: Int,

    @NonNull
    @ColumnInfo(name = "read")
    var read: Boolean = false

) {
    @Embedded
    var contentEntity: ContentEntity? = null
}
