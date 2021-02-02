package com.kotlin.academy.ui.academy

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * @author Rizki Rian Anandita
 * Create By rizki
 */
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}