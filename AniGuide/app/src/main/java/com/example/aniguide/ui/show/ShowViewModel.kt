package com.example.aniguide.ui.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aniguide.kitsu_api.Data
import com.example.aniguide.kitsu_api.KitsuApi
import com.example.aniguide.kitsu_api.KitsuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowViewModel : ViewModel() {


    private val api = KitsuApi.create()
    private val repo = KitsuRepository(api)

    private val season = MutableLiveData<String>().apply { value = "fall" }
    private val year = MutableLiveData<String>().apply { value = "2019" }
    private val offset = MutableLiveData<Int>().apply { value = 20 }

    private val selectedShow = MutableLiveData<String>()
    private val shows = MutableLiveData<List<Data>>().apply { value = ArrayList() }

    fun observeShows(): MutableLiveData<List<Data>> {
        return shows
    }

    fun updateSeason(value: String)
    {
        season.value = value
    }

    fun getSelectedShow(): String
    {
        return selectedShow.value!!
    }

    fun setSelectedShow(value: String)
    {
        selectedShow.value = value
    }

    fun refreshSeasonalShows() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        shows.postValue(repo.getSeasonalShows(season.value!!, year.value!!, offset.value!!))
    }

    fun refreshAllShows() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        shows.postValue(repo.getAllShows(year.value!!, offset.value!!))
    }

}