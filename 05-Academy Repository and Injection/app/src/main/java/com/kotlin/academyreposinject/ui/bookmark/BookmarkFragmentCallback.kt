package com.kotlin.academyreposinject.ui.bookmark

import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
