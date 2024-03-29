package com.example.aniguide.ui.episode

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import com.example.aniguide.tmdb_api.Episode
import com.example.aniguide.tmdb_api.TMDBApi
import com.example.aniguide.tmdb_api.TMDBRepository
import com.example.aniguide.ui.MoreInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    private val api = TMDBApi.create()
    private val repo = TMDBRepository(api)

    private val show = MutableLiveData<String>().apply { value = "Boruto" }
    private val season = MutableLiveData<String>().apply { value = "1" }

    private val episodes = MutableLiveData<List<Episode>>().apply { value = ArrayList() }

    private val searchTerm = MutableLiveData<String?>().apply { value = "" }
    private val searchEpisodes = MediatorLiveData<List<Episode>>()

    init {
        searchEpisodes.addSource(searchTerm) {
            searchEpisodes.value = filterEpisodes(episodes.value!!)
        }
    }

    fun updateSearchTerm(value: String)
    {
        searchTerm.postValue(value)
    }

    private fun filterEpisodes(list: List<Episode>): List<Episode>
    {
        return list.filter { it.getEpisodeFields().contains(searchTerm.value!!, ignoreCase = true)}
    }

    fun observeSearchEpisodes(list: List<Episode>): LiveData<List<Episode>>
    {
        searchEpisodes.value = filterEpisodes(list)
        return searchEpisodes
    }

    fun getSearchEpisodes(): LiveData<List<Episode>>
    {
        return searchEpisodes
    }

    fun updateSeason()
    {
        //Default Season
        season.value = "1"
        //Check if there is a space+number or space + II... for season clues
        var regex = "(\\s[1-9])|(\\sII+)".toRegex()
        val match = regex.find(show.value!!.substring(show.value!!.length/2))
        if(match != null){
            var number = match.value.removePrefix(" ")
            //If number has II...
            if(number.toIntOrNull() == null)
                season.value = number.length.toString()
            //If number is a numeral
            else
                season.value = number
        }
    }

    fun updateShow(value: String)
    {
        show.value = value
    }

    fun observeShow(): LiveData<String> {
        return show
    }

    fun observeEpisodes(): LiveData<List<Episode>> {
        return episodes
    }

    fun refreshEpisodes() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        episodes.postValue(repo.getSeason(show.value.toString(),season.value.toString()))
    }

    companion object {

        const val ep_num_key = "ep_number_key"
        const val name_key = "name_key"
        const val still_path_key = "still_path_key"
        const val overview_key = "overview_key"
        const val air_date_key = "air_data_key"
        const val vote_avg_key = "vote_avg_key"
        const val vote_count_key = "vote_count_key"

        fun showMoreInfo(context: Context, ep: Episode) {

            val moreInfoIntent = Intent(context, MoreInfo::class.java)

            moreInfoIntent.apply {
                putExtra(ep_num_key, ep.episode_number)
                putExtra(name_key, ep.name)
                putExtra(still_path_key, ep.still_path)
                putExtra(overview_key, ep.overview)
                putExtra(air_date_key, ep.air_date)
                putExtra(vote_avg_key, ep.vote_average)
                putExtra(vote_count_key, ep.vote_count)
            }
            context.startActivity(moreInfoIntent)

        }
    }
}