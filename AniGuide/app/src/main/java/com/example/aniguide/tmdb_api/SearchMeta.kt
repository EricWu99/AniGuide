package com.example.aniguide.tmdb_api

import com.google.gson.annotations.SerializedName

data class TMDBSearchMeta (

    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("results") val results : List<SearchResults>
)

data class SearchResults (

    @SerializedName("original_name") val original_name : String,
    @SerializedName("genre_ids") val genre_ids : List<Int>,
    @SerializedName("name") val name : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("origin_country") val origin_country : List<String>,
    @SerializedName("vote_count") val vote_count : Int,
    @SerializedName("first_air_date") val first_air_date : String,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("original_language") val original_language : String,
    @SerializedName("id") val id : Int,
    @SerializedName("vote_average") val vote_average : Float,
    @SerializedName("overview") val overview : String,
    @SerializedName("poster_path") val poster_path : String
)