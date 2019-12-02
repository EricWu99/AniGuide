package com.example.aniguide.ui.spring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpringViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Spring Fragment"
    }
    val text: LiveData<String> = _text
}