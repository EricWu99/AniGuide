package com.example.aniguide.tmdb_api

class TMDBRepository(private val TMDBApi: TMDBApi) {

    suspend fun getSeriesID(title: String): String {
        var searchTitle = title.toLowerCase()

        var indexOfSeason = searchTitle.indexOf("season")
        var indexOfColon = searchTitle.indexOf(":")

        var totalResultsList = TMDBApi.searchShow(searchTitle)
        //If the current title works
        if(totalResultsList.total_results != 0){ return totalResultsList.results[0].id.toString()}
        //Used incase some show starts with the word season
        else if(indexOfSeason > 1){
            searchTitle = title.substring(0,indexOfSeason)
        }
        //Used in case some shows on TMDB dont use :
        else if(indexOfColon > 0){
            searchTitle = title.substring(0,indexOfColon)
        }

        var regex = "[1-9]".toRegex()
        val match = regex.find(searchTitle.substring(searchTitle.length/2))
        if(match != null && match.value.length == 1){
            searchTitle = searchTitle.substringBeforeLast(match.value)
        }

        totalResultsList = TMDBApi.searchShow(searchTitle)
        if(totalResultsList.total_results == 0)
            return "" //Nothing

        return totalResultsList.results[0].id.toString()
    }

    suspend fun getSeason(title: String, season: String): List<Episode>{

        if(this.getSeriesID(title) == "")
            return listOf() //Nothing

        return TMDBApi.getSeason(getSeriesID(title),season).episode
    }
}

