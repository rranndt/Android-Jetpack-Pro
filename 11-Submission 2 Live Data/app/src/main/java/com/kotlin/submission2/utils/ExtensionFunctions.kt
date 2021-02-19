package com.kotlin.submission2.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

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

    fun View.snackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackBar ->
            snackBar.setAction("Try Again") {
                snackBar.dismiss()
            }
        }.show()
    }

}