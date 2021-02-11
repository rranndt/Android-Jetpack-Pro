package com.kotlin.academyidleresources.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.academyidleresources.data.AcademyRepository
import com.kotlin.academyidleresources.di.Injection
import com.kotlin.academyidleresources.ui.academy.AcademyViewModel
import com.kotlin.academyidleresources.ui.bookmark.BookmarkViewModel
import com.kotlin.academyidleresources.ui.detail.DetailCourseViewModel
import com.kotlin.academyidleresources.ui.reader.CourseReaderViewModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}