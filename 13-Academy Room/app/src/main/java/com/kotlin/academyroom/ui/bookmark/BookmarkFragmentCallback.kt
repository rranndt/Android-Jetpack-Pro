package com.kotlin.academyroom.ui.bookmark

import com.kotlin.academyroom.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
