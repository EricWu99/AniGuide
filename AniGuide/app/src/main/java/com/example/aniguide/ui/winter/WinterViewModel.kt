package com.example.aniguide.ui.winter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WinterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Winter Fragment"
    }
    val text: LiveData<String> = _text
}