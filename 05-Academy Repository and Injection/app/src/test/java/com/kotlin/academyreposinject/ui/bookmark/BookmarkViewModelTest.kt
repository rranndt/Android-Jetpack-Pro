package com.kotlin.academyreposinject.ui.bookmark

import com.kotlin.academyreposinject.data.source.local.entity.CourseEntity
import com.kotlin.academyreposinject.data.AcademyRepository
import com.kotlin.academyreposinject.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        Mockito.`when`<ArrayList<CourseEntity>>(academyRepository.getBookmarkedCourses())
            .thenReturn(
                DataDummy.generateDummyCourses() as ArrayList<CourseEntity>
            )
        val courseEntities = viewModel.getBookmarks()
        Mockito.verify<AcademyRepository>(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}