package com.kotlin.academylivedata.ui.bookmark

import com.kotlin.academylivedata.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
