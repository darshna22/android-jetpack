package com.my.android_jetpak.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.my.android_jetpak.R
import com.my.android_jetpak.databinding.ActivityTwoWayDatabindingBinding
import com.my.android_jetpak.model.Contact
import com.my.android_jetpak.utility.Contants
import com.my.android_jetpak.utility.NavigationController
import kotlinx.android.synthetic.main.data_activity.*

class TwoWayDataBinding : AppCompatActivity() {
    private var pageNo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_way_databinding)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityTwoWayDatabindingBinding>(this, R.layout.activity_two_way_databinding)
        val contact = Contact("default", "default@gmail.com")
        activityMainBinding.mContact = contact
        val intent: Intent = getIntent()
        pageNo = intent.getStringExtra(Contants.PAGE_NO)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setTitle(R.string.two_way_data_binding_str)

    }

    fun viewSourceCodeBtn(view: View) {
        NavigationController.navigateToMySourceCodeActivity(this, pageNo)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}