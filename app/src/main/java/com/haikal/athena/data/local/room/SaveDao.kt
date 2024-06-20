package com.haikal.athena.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.haikal.athena.data.local.entity.Save

@Dao
interface SaveDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(save: Save)

    @Update
    fun update(save: Save)

    @Delete
    fun delete(save: Save)

    @Query("SELECT  * from Save")
    fun getAllSave(): LiveData<List<Save>>
}