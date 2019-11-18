package com.example.aniguide.api

import com.google.gson.annotations.SerializedName

data class TMDBSeasonMeta (

    @SerializedName("_id") val _id : String,
    @SerializedName("air_date") val air_date : String,
    @SerializedName("Episode") val Episode : List<Episode>,
    @SerializedName("name") val name : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("id") val id : Int,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("season_number") val season_number : Int
)

data class Episode (

    @SerializedName("air_date") val air_date : String,
    @SerializedName("episode_number") val episode_number : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("production_code") val production_code : String,
    @SerializedName("season_number") val season_number : Int,
    @SerializedName("show_id") val show_id : Int,
    @SerializedName("still_path") val still_path : String,
    @SerializedName("vote_average") val vote_average : Int,
    @SerializedName("vote_count") val vote_count : Int,
    @SerializedName("crew") val crew : List<String>,
    @SerializedName("guest_stars") val guest_stars : List<String>
)