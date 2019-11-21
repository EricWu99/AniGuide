package com.example.aniguide.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aniguide.api.Episode
import com.example.aniguide.api.TMDBApi
import com.example.aniguide.api.SearchListingRepository
import com.example.aniguide.ui.MoreInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val api = TMDBApi.create()
    private val repo = SearchListingRepository(api)

    private val series = MutableLiveData<String>().apply { value = "My+Hero+Academia" }
    private val season = MutableLiveData<String>().apply { value = "1" }

    private val episodes = MutableLiveData<List<Episode>>()

    fun observeSeries(): MutableLiveData<String> {
        return series
    }

    fun observeEpisodes(): MutableLiveData<List<Episode>> {
        return episodes
    }

    fun refreshEpisodes() = viewModelScope.launch(
        context = viewModelScope.coroutineContext
                + Dispatchers.IO) {
        episodes.postValue(repo.getSeason(series.value.toString(),season.value.toString()))
    }

    companion object {

        const val titleKey = "titleKey"
        const val imageKey = "imageKey"
        const val descTextKey = "textKey"

        fun showMoreInfo(context: Context, episode: Episode) {

            val moreInfoIntent = Intent(context, MoreInfo::class.java)

            moreInfoIntent.apply {
                putExtra(titleKey, episode.name)
                putExtra(imageKey, episode.still_path)
                putExtra(descTextKey, episode.overview)
            }
            context.startActivity(moreInfoIntent)

        }
    }
}