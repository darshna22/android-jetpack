package com.my.android_jetpak.room

import androidx.room.*
import com.my.android_jetpak.model.EventData

/**
 * Created by Darshna Kumari on 11,June,2019.
 * darshna.kumari95@outlook.com
 */
@Dao
interface EventDataDao {

    @get:Query("SELECT * from EventData")
    val allEventDataList: MutableList<EventData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventData: EventData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eventDataList: MutableList<EventData>)


    @Update
    fun updateCoinList(eventData: EventData)

    @Delete
    fun deleteRow(eventData: EventData)


    @Query("DELETE FROM EventData")
    fun deleteAll()
}
