package com.kotlin.academyidleresources.ui.bookmark

import com.kotlin.academyidleresources.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
