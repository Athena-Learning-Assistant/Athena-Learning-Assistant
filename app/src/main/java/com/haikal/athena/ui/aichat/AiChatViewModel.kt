package com.haikal.athena.ui.aichat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AiChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Ai Chat Fragment"
    }
    val text: LiveData<String> = _text
}