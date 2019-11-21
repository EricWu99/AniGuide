package com.example.aniguide.api

import android.util.Log
import java.net.URL

class SearchListingRepository(private val TMDBApi: TMDBApi) {
    //val gson = Gson()

//    suspend fun searchShow(title: String): Int {
//        return TMDBApi.searchShow(title).data.results[0].id
//    }

    suspend fun getSeriesID(title: String): String {
        return TMDBApi.searchShow(title).results[0].id.toString()
    }

    suspend fun getSeason(title: String, season: String): List<Episode>{
        return TMDBApi.getSeason(getSeriesID(title),season).episode
    }

}

