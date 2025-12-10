package com.example.movieapp.data.local


import androidx.room.*
import com.example.movieapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM favorites WHERE imdbID = :imdbId")
    suspend fun isFavorite(imdbId: String): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(movie: Movie)

    @Delete
    suspend fun removeFavorite(movie: Movie)

    @Query("DELETE FROM favorites WHERE imdbID = :imdbId")
    suspend fun removeFavoriteById(imdbId: String)
}