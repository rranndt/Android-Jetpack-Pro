package com.kotlin.academyidleresources.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

}