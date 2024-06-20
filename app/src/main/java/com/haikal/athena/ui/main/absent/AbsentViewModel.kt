package com.haikal.athena.ui.main.absent

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haikal.athena.data.local.entity.Save
import com.haikal.athena.data.repository.SaveRepository

class AbsentViewModel(application: Application) : ViewModel() {
    private val mySaveRepository: SaveRepository = SaveRepository(application)

    fun getAllSave(): LiveData<List<Save>> = mySaveRepository.getAllSave()

    fun delete(save: Save) {
        mySaveRepository.delete(save)
    }
}