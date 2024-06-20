package com.haikal.athena.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haikal.athena.data.local.entity.Save

@Database(entities = [Save::class], version = 1)
abstract class SaveDatabase : RoomDatabase() {
    abstract fun saveDao(): SaveDao

    companion object {
        @Volatile
        private var INSTANCE: SaveDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SaveDatabase {
            if (INSTANCE == null) {
                synchronized(SaveDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SaveDatabase::class.java, "save_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as SaveDatabase
        }
    }
}