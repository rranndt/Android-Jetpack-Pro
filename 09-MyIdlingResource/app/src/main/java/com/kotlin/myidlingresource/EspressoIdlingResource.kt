package com.kotlin.myidlingresource

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object EspressoIdlingResource {

    private val RESOURCE: String? = "GLOBAL"
    private val espressoTextIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTextIdlingResource.increment()
    }

    fun decrement() {
        espressoTextIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTextIdlingResource
    }

}