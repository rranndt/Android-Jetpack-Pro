package com.kotlin.finalsub.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.utils.Constant.Companion.DATABASE_NAME

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Database(
    entities = [MoviesEntity::class, TvSeriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun catalogueDao(): CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CatalogueDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
            }
    }

}