package com.my.android_jetpak.listeners

interface MyResponse {
    fun onErrorObtained(error: String, requestNo: Int)

    fun onResponseObtained(response: String, requestNo: Int)

}
