package com.my.android_jetpak.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventResponse {

    @SerializedName("data")
    @Expose
    var data: MutableList<EventData>? = null
    @SerializedName("count")
    @Expose
    var count: Int = 0
    @SerializedName("page")
    @Expose
    var page: Int = 0

}
