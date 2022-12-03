package com.example.movieappcompose.data.datasources.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappcompose.data.models.MovieTable

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    suspend fun getWatchlistMovies(): List<MovieTable>

    @Query("SELECT EXISTS(SELECT* from movie WHERE id = :id)")
    suspend fun isWatchlist(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: MovieTable)

    @Query("DELETE from movie WHERE id = :id")
    suspend fun removeMovie(id: Int)
}