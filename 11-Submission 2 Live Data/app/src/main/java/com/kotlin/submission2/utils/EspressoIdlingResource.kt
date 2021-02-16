package com.kotlin.submission2.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingResource.increment()
    fun decrement() = espressoIdlingResource.decrement()

}