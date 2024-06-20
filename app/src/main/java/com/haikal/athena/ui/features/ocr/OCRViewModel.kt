package com.haikal.athena.ui.features.ocr

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OCRViewModel(application: Application) : AndroidViewModel(application) {

    private val _pdfUri = MutableLiveData<Uri?>()
    val pdfUri: LiveData<Uri?> = _pdfUri

    private val _conversionSuccess = MutableLiveData<Boolean>()
    val conversionSuccess: LiveData<Boolean> = _conversionSuccess

    fun setPdfUri(uri: Uri) {
        _pdfUri.value = uri
        simulateConversion()
    }

    private fun simulateConversion() {
        _conversionSuccess.value = true
    }
}