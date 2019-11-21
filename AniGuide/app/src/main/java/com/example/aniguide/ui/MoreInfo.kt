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
import kotlinx.android.synthetic.main.content_more_info.*

class MoreInfo : AppCompatActivity() {

    fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val customView: View =
            layoutInflater.inflate(R.layout.more_info_bar, null)
        // Apply the custom view
        actionBar.customView = customView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }

        val title = intent.getStringExtra(HomeViewModel.titleKey)

        ep_title.text = title

        val imageUrl = intent.getStringExtra(HomeViewModel.imageKey)
        Glide.glideFetch("https://image.tmdb.org/t/p/w500$imageUrl",
            "https://image.tmdb.org/t/p/w500$imageUrl", ep_image)

        ep_desc.text = intent.getStringExtra(HomeViewModel.descTextKey)
        ep_desc.movementMethod = ScrollingMovementMethod()
    }

    override fun onBackPressed() {

        finish()
    }

}
