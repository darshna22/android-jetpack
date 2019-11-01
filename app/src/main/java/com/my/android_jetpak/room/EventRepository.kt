package com.my.android_jetpak.room

import android.app.Application
import android.os.AsyncTask
import com.my.android_jetpak.model.EventData
import com.my.android_jetpak.utility.ThreadManager
import java.util.concurrent.ExecutionException

/**
 * Created by Darshna Kumari on 11,June,2019.
 * darshnakumari95@outlook.com
 */

class EventRepository(activity: Application) {
    private val eventDataDao: EventDataDao

    val allEventListData: List<EventData>
        @Throws(ExecutionException::class, InterruptedException::class)
        get() = GetAllAsyncTask(eventDataDao).execute().get()


    init {
        eventDataDao = DatabaseClient.getInstance(activity)
            .appDatabase
            .eventDataDao()
    }


    fun insertAll(list: MutableList<EventData>) {
        ThreadManager.instance?.doWork(object : ThreadManager.CustomRunnable() {


            override fun onBackground() {
                eventDataDao.insertAll(list)

            }

            override fun onUi() {
                super.onUi()
            }
        })
    }

    fun updateCoinList(eventData: EventData) {

        ThreadManager.instance?.doWork(object : ThreadManager.CustomRunnable() {


            override fun onBackground() {
                eventDataDao.updateCoinList(eventData)

            }

            override fun onUi() {
                super.onUi()
            }
        })
    }

    fun deleteRow(coin: EventData) {
        ThreadManager.instance?.doWork(object : ThreadManager.CustomRunnable() {


            override fun onBackground() {
                eventDataDao.deleteRow(coin)

            }

            override fun onUi() {
                super.onUi()
            }
        })
    }

    fun deleteAll() {

        ThreadManager.instance?.doWork(object : ThreadManager.CustomRunnable() {


            override fun onBackground() {
                eventDataDao.deleteAll()

            }

            override fun onUi() {
                super.onUi()
            }
        })
    }


    private class GetAllAsyncTask internal constructor(private val mAsyncTaskDao: EventDataDao) :
        AsyncTask<Void, Void, MutableList<EventData>>() {
        override fun doInBackground(vararg voids: Void): MutableList<EventData> {
            return mAsyncTaskDao.allEventDataList
        }


    }

}
