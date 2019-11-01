package com.my.android_jetpak.utility

import android.app.Application
import io.github.kbiakov.codeview.classifier.CodeProcessor

class MyApp : Application() {

    companion object {
        lateinit var myApplication: com.my.android_jetpak.utility.MyApp
    }

    override fun onCreate() {
        super.onCreate()
        com.my.android_jetpak.utility.MyApp.Companion.myApplication = this
        // train classifier on app start
        CodeProcessor.init(this)
    }

}