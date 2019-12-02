package com.example.aniguide.ui.fall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FallViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fall Fragment"
    }
    val text: LiveData<String> = _text
}