package com.example.movieapp.data.remote


import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = "57f2ef9f",
        @Query("s") searchQuery: String,
        @Query("page") page: Int = 1
    ): SearchResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String = "57f2ef9f",
        @Query("i") imdbId: String,
        @Query("plot") plot: String = "full"
    ): Movie
}