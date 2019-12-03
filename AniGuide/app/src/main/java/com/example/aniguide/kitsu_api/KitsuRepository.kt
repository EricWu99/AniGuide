package com.example.aniguide.kitsu_api

import android.util.Log

class KitsuRepository(private val KitsuApi: KitsuApi) {

    suspend fun getSeasonalShows(season: String, year: String, offset: Int): List<Data>{
        val postList: MutableList<Data> = ArrayList()

        val childResponses = KitsuApi.getSeasonalShows(season,year,offset).data
        childResponses.forEach {
            postList.add(it)
        }
        return postList
    }

    suspend fun getAllShows(year: String, offset: Int): List<Data>{
        val postList: MutableList<Data> = ArrayList()

        val childResponses = KitsuApi.getAllShows(year,offset).data
        childResponses.forEach {
            postList.add(it)
        }
        return postList
    }

}

