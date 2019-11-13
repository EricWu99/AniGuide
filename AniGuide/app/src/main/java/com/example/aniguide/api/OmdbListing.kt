package com.example.aniguide.api

import com.google.gson.annotations.SerializedName

data class OmdbListing (
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("rated")
    val rated: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("season")
    val seasont: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Awards")
    val awards: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Ratings")
    val ratings: String,
    @SerializedName("Metascore")
    val metascore: String,
    @SerializedName("imdbrating")
    val imdbRating: String,
    @SerializedName("imdbvotes")
    val imdbVotes: String,
    @SerializedName("imdbID")
    val imdbID: String
)
