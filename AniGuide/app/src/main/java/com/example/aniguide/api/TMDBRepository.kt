package com.example.aniguide.api

import android.util.Log
import java.net.URL

class SearchListingRepository(private val TMDBApi: TMDBApi) {
    //val gson = Gson()

//    suspend fun searchShow(title: String): Int {
//        return TMDBApi.searchShow(title).data.results[0].id
//    }

    suspend fun searchShow(title: String): TMDBSearchMeta {
        return TMDBApi.searchShow(title)
    }

    suspend fun getSeason(id: String, title: String, season: String): List<Episode>{
        return TMDBApi.getSeason(id ,season).episode
    }

}

