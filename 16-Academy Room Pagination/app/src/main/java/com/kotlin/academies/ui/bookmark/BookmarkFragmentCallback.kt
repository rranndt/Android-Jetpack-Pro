package com.kotlin.academies.ui.bookmark


import com.kotlin.academies.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

