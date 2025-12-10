package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search")
    val search: List<SearchResult>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String
)

data class SearchResult(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String
)

@Entity(tableName = "favorites")
data class Movie(
    @PrimaryKey
    @SerializedName("imdbID")
    val imdbID: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Rated")
    val rated: String?,

    @SerializedName("Released")
    val released: String?,

    @SerializedName("Runtime")
    val runtime: String?,

    @SerializedName("Genre")
    val genre: String?,

    @SerializedName("Director")
    val director: String?,

    @SerializedName("Writer")
    val writer: String?,

    @SerializedName("Actors")
    val actors: String?,

    @SerializedName("Plot")
    val plot: String?,

    @SerializedName("Language")
    val language: String?,

    @SerializedName("Country")
    val country: String?,

    @SerializedName("Awards")
    val awards: String?,

    @SerializedName("Poster")
    val poster: String?,

    @SerializedName("imdbRating")
    val imdbRating: String?,

    @SerializedName("imdbVotes")
    val imdbVotes: String?,

    @SerializedName("BoxOffice")
    val boxOffice: String?,

    @SerializedName("Response")
    val response: String?
)