package com.example.aniguide.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.aniguide.R
import com.example.aniguide.glide.Glide
import com.example.aniguide.ui.episode.EpisodeViewModel
import kotlinx.android.synthetic.main.activity_more_info.*
import kotlinx.android.synthetic.main.content_more_info.*
import kotlinx.android.synthetic.main.more_info_bar.*

class MoreInfo : AppCompatActivity() {

    fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val customView: View = layoutInflater.inflate(R.layout.more_info_bar, null)
        actionBar.customView = customView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_more_info)

        setSupportActionBar(toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        //Set the episode number as the action bar title
        val epNumber = intent.getIntExtra(EpisodeViewModel.ep_num_key, 0)
        episodeTitle.text = "Episode $epNumber"

        //Display the name of the episode
        ep_name.text = intent.getStringExtra(EpisodeViewModel.name_key)

        //Display the image associated with the episode
        val imageUrl = intent.getStringExtra(EpisodeViewModel.still_path_key)
        Glide.glideFetch("https://image.tmdb.org/t/p/w500$imageUrl",
            "https://image.tmdb.org/t/p/w500$imageUrl", ep_still)

        //Display the overview text for the episode
        ep_overview.text = intent.getStringExtra(EpisodeViewModel.overview_key)

        //Display the air date and vote average for the episode
        ep_air_date.text = "Aired on ${intent.getStringExtra(EpisodeViewModel.air_date_key)}"
        ep_vote_average.text = "Voted ${intent.getFloatExtra(EpisodeViewModel.vote_avg_key, 0f)}/10.0" +
                " by ${intent.getIntExtra(EpisodeViewModel.vote_count_key, 0)} users"
    }

    fun backButton(view: View) {

        finish()
    }
    override fun onBackPressed() {

        finish()
    }
}
