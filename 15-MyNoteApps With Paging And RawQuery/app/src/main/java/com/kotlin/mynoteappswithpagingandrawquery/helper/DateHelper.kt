package com.kotlin.mynoteappswithpagingandrawquery.helper

import java.text.SimpleDateFormat
import java.util.*

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object DateHelper {

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

}