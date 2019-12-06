package com.example.aniguide.ui.show

import android.util.Log
import androidx.lifecycle.*
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.kitsu_api.KitsuApi
import com.example.aniguide.kitsu_api.KitsuRepository
import com.example.aniguide.kitsu_api.Shows
import com.example.aniguide.tmdb_api.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowViewModel : ViewModel() {


    private val api = KitsuApi.create()
    private val repo = KitsuRepository(api)

    private val season = MutableLiveData<String>().apply { value = "fall" }
    private val year = MutableLiveData<String>().apply { value = "2019" }
    private val offset = MutableLiveData<Int>().apply { value = 0 }

    private val selectedShow = MutableLiveData<String>()
    private val shows = MutableLiveData<List<Data>>().apply { value = ArrayList() }

    private val searchTerm = MutableLiveData<String?>().apply { value = "" }
    private val searchShows = MediatorLiveData<List<Data>>()

    private val maxLimit = 20

    init {
        searchShows.addSource(searchTerm) {
            searchShows.value = filterEpisodes(shows.value!!)
        }
    }

    fun updateSearchTerm(value: String)
    {
        searchTerm.postValue(value)
        Log.d("XXX", "$value")
    }

    private fun filterEpisodes(list: List<Data>): List<Data>
    {
        return list.filter { it.attributes.getShowFields().contains(searchTerm.value!!, ignoreCase = true)}
    }

    fun observeSearchShows(list: List<Data>): LiveData<List<Data>>
    {
        searchShows.value = filterEpisodes(list)
        return searchShows
    }

    fun getSearchShows(): LiveData<List<Data>>
    {
        return searchShows
    }

    fun observeShows(): LiveData<List<Data>> {
        return shows
    }

    fun updateSeason(value: String)
    {
        season.value = value
    }

    fun updateOffset(reset: Boolean)
    {
        if(reset == false)
            offset.value = offset.value!!.plus(maxLimit)
        else
            offset.value = 0

    }

    fun observeSelectedShow(): LiveData<String>
    {
        return selectedShow
    }

    fun updateSelectedShow(value: String)
    {
        selectedShow.value = value
    }

    fun resetSeasonal() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        shows.postValue(repo.getSeasonalShows(season.value!!, year.value!!, offset.value!!))
    }

    fun resetAllShows() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        shows.postValue(repo.getAllShows(year.value!!, offset.value!!))
    }

    fun refreshSeasonalShows() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        if(!shows.value.isNullOrEmpty()) {
            var show = shows.value!!.toMutableList()
            show.addAll(show.size , repo.getSeasonalShows(season.value!!, year.value!!, offset.value!!))
            shows.postValue(show)
        }
        else{ shows.postValue(repo.getSeasonalShows(season.value!!, year.value!!, offset.value!!)) }
    }

    fun refreshAllShows() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        if(!shows.value.isNullOrEmpty()) {
            var show = shows.value!!.toMutableList()
            show.addAll(show.size ,repo.getAllShows(year.value!!, offset.value!!))
            shows.postValue(show)
        }
        else{ shows.postValue(repo.getAllShows(year.value!!, offset.value!!)) }
    }

}