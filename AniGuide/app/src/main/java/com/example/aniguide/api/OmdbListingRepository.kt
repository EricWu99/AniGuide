package com.example.aniguide.api

import com.example.aniguide.MainActivity
import com.google.gson.Gson

class OmdbListingRepository(private val OmdbApi: OmdbApi) {
    //val gson = Gson()

    suspend fun getEpisode(title: String, season:String, episode:String): OmdbListing {
        return OmdbApi.getEpisode(title,season,episode).data
    }

    //This is for testing without season and episode
    suspend fun getSeries(title: String): OmdbListing {
        return OmdbApi.getSeries(title).data
    }
}

