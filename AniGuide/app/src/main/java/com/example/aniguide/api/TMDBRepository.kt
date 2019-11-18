package com.example.aniguide.api

import android.util.Log

class SearchListingRepository(private val TMDBApi: TMDBApi) {
    //val gson = Gson()

//    suspend fun searchShow(title: String): Int {
//        return TMDBApi.searchShow(title).data.results[0].id
//    }

    suspend fun searchShow(title: String): TMDBSearchMeta {
        return TMDBApi.searchShow(title).data
    }

    suspend fun getSeason(season: String, title: String): List<Episode> {
        return TMDBApi.getSeason((searchShow(title)).toString(),season).data.Episode
    }

}

