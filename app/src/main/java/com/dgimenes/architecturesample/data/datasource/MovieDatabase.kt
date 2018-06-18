package com.dgimenes.architecturesample.data.datasource

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dgimenes.architecturesample.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        private const val DB_NAME = "movie.db"

        private var _instance: MovieDatabase? = null

        fun getInstance(appContext: Context): MovieDatabase {
            if (_instance == null) {
                _instance = Room.databaseBuilder(
                        appContext,
                        MovieDatabase::class.java,
                        DB_NAME)
                        .build()
            }
            return _instance!!
        }
    }

}