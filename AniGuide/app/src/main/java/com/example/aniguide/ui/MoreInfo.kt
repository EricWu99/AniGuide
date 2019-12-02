package com.example.aniguide.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.aniguide.R
import com.example.aniguide.glide.Glide
import com.example.aniguide.ui.home.HomeViewModel
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
        setContentView(R.layout.activity_more_info)

        setSupportActionBar(toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        //Set the episode number as the action bar title
        val epNumber = intent.getIntExtra(HomeViewModel.ep_num_key, 0)
        episodeTitle.text = "Episode $epNumber"

        //Display the name of the episode
        ep_name.text = intent.getStringExtra(HomeViewModel.name_key)

        //Display the image associated with the episode
        val imageUrl = intent.getStringExtra(HomeViewModel.still_path_key)
        Glide.glideFetch("https://image.tmdb.org/t/p/w500$imageUrl",
            "https://image.tmdb.org/t/p/w500$imageUrl", ep_still)

        //Display the overview text for the episode
        ep_overview.text = intent.getStringExtra(HomeViewModel.overview_key)
        ep_overview.movementMethod = ScrollingMovementMethod()

        //Display the air date and vote average for the episode
        ep_air_date.text = "Aired on ${intent.getStringExtra(HomeViewModel.air_date_key)}"
        ep_vote_average.text = "Voted ${intent.getFloatExtra(HomeViewModel.vote_avg_key, 0f)}/10.0" +
                " by ${intent.getIntExtra(HomeViewModel.vote_count_key, 0)} users"

    }

    fun backButton(view: View) {

        finish()
    }
    override fun onBackPressed() {

        finish()
    }

}
