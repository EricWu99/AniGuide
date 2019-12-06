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

        var regex =  "(\\s[1-9])|(\\sii+)".toRegex()
        val match = regex.find(searchTitle.substring(searchTitle.length/2))
        if(match != null){
            searchTitle = searchTitle.substringBeforeLast(match.value)
        }

        totalResultsList = TMDBApi.searchShow(searchTitle)
        if(totalResultsList.total_results == 0)
            return "" //Nothing

        return totalResultsList.results[0].id.toString()
    }

    suspend fun getNumSeasons(titleID: String): Int{
        return TMDBApi.getNumSeasons(titleID).number_of_seasons
    }

    suspend fun getSeason(title: String, season: String): List<Episode>{
        var titleID = this.getSeriesID(title)
        if(titleID == "")
            return listOf() //Nothing
        //Incase we Kitsu says the season is X but TMDB lists them differently (All seasons as 1) so default to 1
        var numSeasons = getNumSeasons(titleID)
        if(season.toInt() <= numSeasons)
            return TMDBApi.getSeason(titleID,season).episode
        else
            return TMDBApi.getSeason(titleID,"1").episode
    }

}

