package com.kotlin.submission2.utils

import android.view.View

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object ExtensionFunctions {

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

}