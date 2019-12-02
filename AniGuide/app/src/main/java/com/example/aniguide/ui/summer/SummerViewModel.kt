package com.example.aniguide.ui.summer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SummerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Summer Fragment"
    }
    val text: LiveData<String> = _text
}