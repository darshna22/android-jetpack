package com.my.android_jetpak.room

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room

class DatabaseClient private constructor(mCtx: Application) {

    //our app database object
    val appDatabase: MyAppDatabase

    init {

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, MyAppDatabase::class.java, "event_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()// use to execute task on main thread
            .build()
        print("data is here=" + appDatabase)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Application): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance as DatabaseClient
        }
    }


}
