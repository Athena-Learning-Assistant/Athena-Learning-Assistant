package com.haikal.athena.ui.features.cam

import android.app.Application
import androidx.lifecycle.ViewModel
import com.haikal.athena.data.local.entity.Save
import com.haikal.athena.data.repository.SaveRepository

class ResultViewModel(application: Application) : ViewModel() {
    private val saveRepository: SaveRepository = SaveRepository(application)

    fun insert(save: Save) {
        saveRepository.insert(save)
    }
}