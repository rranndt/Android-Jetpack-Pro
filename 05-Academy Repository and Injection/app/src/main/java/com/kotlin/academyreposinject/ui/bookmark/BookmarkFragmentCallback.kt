package com.kotlin.academyreposinject.ui.bookmark

import com.kotlin.academyreposinject.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
