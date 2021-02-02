package com.kotlin.academy.ui.bookmark

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks() {
        val courseEntities = viewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}