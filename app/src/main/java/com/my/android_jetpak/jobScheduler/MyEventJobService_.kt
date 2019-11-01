package com.my.android_jetpak.jobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import android.R
import android.app.PendingIntent
import android.content.Intent
import com.my.android_jetpak.activity.MainActivity
import com.my.android_jetpak.listeners.MyResponse
import com.my.android_jetpak.room.EventRepository
import com.my.android_jetpak.model.EventResponse
import com.my.android_jetpak.volley.ApiController
import com.google.gson.Gson
import com.my.android_jetpak.utility.AndroidAppUtils


/**
 * Created by Darshna Kumari on 18,June,2019.
 * darshnakumari95@outlook.com
 */

class MyEventJobService_ : JobService(), MyResponse {
    var mNotifyManager: NotificationManager? = null
    private var eventRepository: EventRepository? = null
    // Notification channel ID.
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    companion object {
        private val TAG = "MyJobService"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Toast.makeText(this, "Job Stopped: criteria not met", Toast.LENGTH_SHORT).show();
        return false;
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        print("Schedule service Successfully")
        Toast.makeText(this, "Job Starts", Toast.LENGTH_SHORT).show();
        getEventData()
        return false
    }

    private fun getEventData() {
        ApiController(this, ApiController.COIN_EVENT).hitCoinEventApi()
    }

    fun showNotification(notificationMsg: String) {
        createNotificationChannel()

        //Set up the notification content intent to launch the app when clicked
        val contentPendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Event Data Service")
            .setContentText(notificationMsg)
            .setContentIntent(contentPendingIntent)
            .setSmallIcon(R.drawable.ic_delete)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)

        mNotifyManager?.notify(0, builder.build())


    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    fun createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Job Service notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifications from Job Service"

            mNotifyManager!!.createNotificationChannel(notificationChannel)
        }
    }

    override fun onErrorObtained(error: String, requestNo: Int) {
        showNotification("Event Data Update failed!")
    }

    override fun onResponseObtained(response: String, requestNo: Int) {
        AndroidAppUtils.hideProgressDialog()
        val eventResponse = Gson().fromJson<EventResponse>(response, EventResponse::class.java)
//        if (eventResponse != null && eventResponse.data != null && !eventResponse.data!!.isEmpty()) {
//            eventRepository = EventRepository(MyApp.myApplication)
//
//            eventRepository!!.deleteAll()
//            eventRepository!!.insertAll(eventResponse.data as MutableList<EventData>)
//            showNotification("New Event Data Uploaded with " + eventResponse.data!!.size)
//        }
    }


}