package com.example.movieappcompose.data.datasources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappcompose.data.models.MovieTable

@Database(entities = [MovieTable::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_database",
            ).build()
        }.also { instance = it }
    }
}