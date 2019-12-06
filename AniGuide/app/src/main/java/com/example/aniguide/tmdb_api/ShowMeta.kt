package com.example.aniguide.tmdb_api

import com.google.gson.annotations.SerializedName


data class TMDBShowMeta (
    @SerializedName("number_of_seasons") val number_of_seasons : Int
)