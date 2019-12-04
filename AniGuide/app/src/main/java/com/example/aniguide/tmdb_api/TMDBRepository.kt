package com.example.aniguide.tmdb_api

class TMDBRepository(private val TMDBApi: TMDBApi) {

    suspend fun getSeriesID(title: String): String {

        if(TMDBApi.searchShow(title).total_results == 0)
            return "" //Nothing

        return TMDBApi.searchShow(title).results[0].id.toString()
    }

    suspend fun getSeason(title: String, season: String): List<Episode>{

        if(this.getSeriesID(title) == "")
            return listOf() //Nothing

        return TMDBApi.getSeason(getSeriesID(title),season).episode
    }

}

