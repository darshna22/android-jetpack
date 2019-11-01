package com.my.android_jetpak.mvvm
import androidx.databinding.BaseObservable


class MVVMEvent: BaseObservable()  {

    var type = ""
    var title = ""
    var description = ""
    var organizer = ""
    var startDate = ""
    var endDate = ""
    var website = ""
    var email = ""
    var venue = ""
    var address = ""
    var city = ""
    var country = ""
    public final var screenshot = ""

}
