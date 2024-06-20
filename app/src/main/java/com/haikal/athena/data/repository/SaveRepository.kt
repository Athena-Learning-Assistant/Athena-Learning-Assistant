package com.haikal.athena.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.haikal.athena.data.local.entity.Save
import com.haikal.athena.data.local.room.SaveDao
import com.haikal.athena.data.local.room.SaveDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SaveRepository(application: Application) {
    private val mySaveDao: SaveDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SaveDatabase.getDatabase(application)
        mySaveDao = db.saveDao()
    }

    fun getAllSave(): LiveData<List<Save>> = mySaveDao.getAllSave()

    fun insert(save: Save) {
        executorService.execute { mySaveDao.insert(save) }
    }

    fun delete(note: Save) {
        executorService.execute { mySaveDao.delete(note) }
    }
}