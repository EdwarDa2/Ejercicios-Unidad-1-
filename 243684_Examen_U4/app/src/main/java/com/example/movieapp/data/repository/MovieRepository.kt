package com.example.movieapp.data.repository

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.SearchResponse
import com.example.movieapp.data.remote.MovieApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) {
    suspend fun searchMovies(query: String): SearchResponse {
        return api.searchMovies(searchQuery = query)
    }

    suspend fun getMovieDetails(imdbId: String): Movie {
        return api.getMovieDetails(imdbId = imdbId)
    }

    fun getFavorites(): Flow<List<Movie>> = dao.getFavorites()

    suspend fun isFavorite(imdbId: String) = dao.isFavorite(imdbId) != null

    suspend fun addFavorite(movie: Movie) = dao.addFavorite(movie)

    suspend fun removeFavorite(movie: Movie) = dao.removeFavorite(movie)
}