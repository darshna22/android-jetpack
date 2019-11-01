package com.my.android_jetpak.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.my.android_jetpak.listeners.MyResponse
import com.my.android_jetpak.model.EventData
import com.my.android_jetpak.model.EventResponse
import com.my.android_jetpak.utility.AndroidAppUtils
import com.my.android_jetpak.volley.ApiController

class DataModelView : ViewModel(), MyResponse {

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    //this is the data that we will fetch asynchronously
    private var eventList: MutableLiveData<List<EventData>>? = null

    //we will call this method to get the data
    fun getScratchData(context: Context): LiveData<List<EventData>> {
        //if the list is null
        if (eventList == null) {
            this.context = context;
            eventList = MutableLiveData()
        }

        //we will load it asynchronously from server in this method
        hitAPI()
        //finally we will return the list
        return eventList as MutableLiveData<List<EventData>>
    }


    private fun hitAPI() {
        ApiController(this, ApiController.COIN_EVENT)
            .hitCoinEventApi()

    }


    override fun onErrorObtained(error: String, requestNo: Int) {
        AndroidAppUtils.hideProgressDialog()

    }

    override fun onResponseObtained(response: String, requestNo: Int) {
        AndroidAppUtils.hideProgressDialog()
        println("list data here=" + response)
        val eventResponse = Gson().fromJson<EventResponse>(response, EventResponse::class.java)
        if (eventResponse != null && eventResponse.data != null && !eventResponse.data!!.isEmpty()) {

            println("data is here"+eventResponse)
            eventList!!.setValue(eventResponse.data)
        }
    }


}
