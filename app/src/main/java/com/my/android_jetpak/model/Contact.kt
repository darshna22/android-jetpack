package com.my.android_jetpak.model
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


class Contact(internal var name: String, internal var email: String) : BaseObservable() {

    @Bindable
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
        notifyPropertyChanged(BR.email)

    }

}
