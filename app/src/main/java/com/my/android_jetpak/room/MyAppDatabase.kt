package com.my.android_jetpak.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.android_jetpak.model.EventData

@Database(entities = [EventData::class], version = 1, exportSchema = false)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun eventDataDao(): EventDataDao
}
