package com.kotlin.submission2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Parcelize
data class DataEntity(

    var id: String,

    var title: String,

    var description: String,

    var genre: String,

    var yearRelease: String,

    var rating: String,

    var directorOrCreator: String,

    var stars: String,

    var metascore: Float,

    var reviews: String,

    var popularity: String,

    var bgHeader: Int,

    var bgPoster: Int

) : Parcelable