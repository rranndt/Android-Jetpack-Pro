package com.kotlin.academyroom.data.source.local.entity

import androidx.room.ColumnInfo

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
data class ContentEntity(

    @ColumnInfo(name = "content")
    var content: String?

)
