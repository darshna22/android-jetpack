package com.my.android_jetpak.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class EventData : Serializable {
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @Expose
    @SerializedName("type")
    lateinit var type: String

    @Expose
    @SerializedName("title")
    lateinit var title: String

    @Expose
    @SerializedName("description")
    lateinit var description: String

    @Expose
    @SerializedName("organizer")
    lateinit var organizer: String

    @Expose
    @SerializedName("start_date")
    lateinit var startDate: String

    @Expose
    @SerializedName("end_date")
    lateinit var endDate: String

    @Expose
    @SerializedName("website")
    lateinit var website: String

    @Expose
    @SerializedName("email")
    lateinit var email: String

    @Expose
    @SerializedName("venue")
    lateinit var venue: String

    @Expose
    @SerializedName("address")
    lateinit var address: String

    @Expose
    @SerializedName("city")
    lateinit var city: String

    @Expose
    @SerializedName("country")
    lateinit var country: String

    @Expose
    @SerializedName("screenshot")
    lateinit var screenshot: String

}
