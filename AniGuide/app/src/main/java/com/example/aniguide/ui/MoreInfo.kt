package com.example.aniguide.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.aniguide.R
import com.example.aniguide.glide.Glide
import com.example.aniguide.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.content_more_info.*

class MoreInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val title = intent.getStringExtra(HomeViewModel.titleKey)

        ep_title.text = title

        val imageUrl = intent.getStringExtra(HomeViewModel.imageKey)
        Glide.glideFetch(imageUrl, imageUrl, ep_image)

        ep_desc.text = intent.getStringExtra(HomeViewModel.descTextKey)
        ep_desc.movementMethod = ScrollingMovementMethod()
    }

    override fun onBackPressed() {

        finish()
    }

}
