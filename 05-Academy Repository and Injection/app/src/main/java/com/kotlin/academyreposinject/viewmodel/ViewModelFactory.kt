package com.kotlin.academyreposinject.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.academyreposinject.data.source.AcademyRepository
import com.kotlin.academyreposinject.di.Injection
import com.kotlin.academyreposinject.ui.academy.AcademyViewModel
import com.kotlin.academyreposinject.ui.bookmark.BookmarkViewModel
import com.kotlin.academyreposinject.ui.detail.DetailCourseViewModel
import com.kotlin.academyreposinject.ui.reader.CourseReaderViewModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstace(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}