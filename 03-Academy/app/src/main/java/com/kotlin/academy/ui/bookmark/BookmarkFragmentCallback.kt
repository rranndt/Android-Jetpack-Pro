package com.kotlin.academy.ui.bookmark

import com.kotlin.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
