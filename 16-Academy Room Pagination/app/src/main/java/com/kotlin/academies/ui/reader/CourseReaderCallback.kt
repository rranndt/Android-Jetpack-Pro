package com.kotlin.academies.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}
