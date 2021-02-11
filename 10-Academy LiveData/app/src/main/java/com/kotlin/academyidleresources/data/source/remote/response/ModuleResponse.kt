package com.kotlin.academyidleresources.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Parcelize
data class ModuleResponse(

    var moduleId: String,

    var courseId: String,

    var title: String,

    var position: Int

) : Parcelable