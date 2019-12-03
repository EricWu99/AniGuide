package com.example.aniguide.tmdb_api

class TMDBRepository(private val TMDBApi: TMDBApi) {

    suspend fun getSeriesID(title: String): String {
        return TMDBApi.searchShow(title).results[0].id.toString()
    }

    suspend fun getSeason(title: String, season: String): List<Episode>{
        return TMDBApi.getSeason(getSeriesID(title),season).episode
    }

}

