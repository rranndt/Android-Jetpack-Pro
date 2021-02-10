package com.kotlin.academylivedata.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Parcelize
data class CourseResponse(

    var id: String,

    var title: String,

    var description: String,

    var date: String,

    var imagePath: String

) : Parcelable
