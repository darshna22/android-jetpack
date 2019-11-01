package com.my.android_jetpak.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.android_jetpak.R
import com.my.android_jetpak.listeners.MyResponse
import kotlinx.android.synthetic.main.activity_main_bottom.*

class LiveDataActivity :AppCompatActivity(), MyResponse {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_fragment_main)
        setSupportActionBar(mToolbar)

    }
    override fun onErrorObtained(error: String, requestNo: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponseObtained(response: String, requestNo: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}