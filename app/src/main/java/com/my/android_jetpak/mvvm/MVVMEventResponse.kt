package com.my.android_jetpak.mvvm
import androidx.databinding.BaseObservable

class MVVMEventResponse : BaseObservable() {

    var data: List<MVVMEvent>? = null
    var count: Int = 0
    var page: Int = 0

}
