package com.my.android_jetpak.utility

import android.app.Activity
import android.content.Intent
import com.my.android_jetpak.activity.*
import com.my.android_jetpak.utility.Contants.Companion.PAGE_NO
import com.my.android_jetpak.utility.Contants.Companion.APP_SHARE_URL
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.my.android_jetpak.R
import com.my.android_jetpak.utility.Contants.Companion.WEBSITE_URL


object NavigationController {
    fun navigateToDataBindingActivity(activity: Activity) {
        val intent: Intent = Intent(activity, DataBindingActivity::class.java)
        intent.putExtra(PAGE_NO, "0")
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun navigateToTwoWayDataBinding(activity: Activity) {
        val intent: Intent = Intent(activity, TwoWayDataBinding::class.java)
        intent.putExtra(PAGE_NO, "1")
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun navigateToBottomNavigationActivity(activity: Activity) {
        val intent: Intent = Intent(activity, BottomNavigationActivity::class.java)
        intent.putExtra(PAGE_NO, "2")
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun navigateToRoomEventActivity(activity: Activity) {
        val intent: Intent = Intent(activity, RoomEventActivity::class.java)
        intent.putExtra(PAGE_NO, "3")
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun navigateToMySourceCodeActivity(activity: Activity, pageNo: String) {
        val intent: Intent = Intent(activity, MySourceCodeActivity::class.java)
        intent.putExtra(PAGE_NO, pageNo)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun navigateToMVVMActivity(activity: Activity, pageNo: String) {
        val intent: Intent = Intent(activity, MVVMActivity::class.java)
        intent.putExtra(PAGE_NO, pageNo)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    fun shareApplication(activity: Activity) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            APP_SHARE_URL
        )
        sendIntent.type = "text/plain"
        activity.startActivity(sendIntent)
    }


    fun navigateToRateUs(activity: Activity) {
        val rateIntent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + activity.getPackageName())
            )
        activity.startActivity(rateIntent)
    }
    fun navigateToWebsite(activity: Activity){
        val browserIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse(WEBSITE_URL))
        activity.startActivity(browserIntent)
    }

}