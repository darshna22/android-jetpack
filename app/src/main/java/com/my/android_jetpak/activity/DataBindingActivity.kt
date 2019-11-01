package com.my.android_jetpak.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.my.android_jetpak.R
import androidx.databinding.DataBindingUtil
import com.my.android_jetpak.BR
import com.my.android_jetpak.databinding.DataActivityBinding
import com.my.android_jetpak.model.MyData
import com.my.android_jetpak.utility.Contants
import com.my.android_jetpak.utility.NavigationController
import kotlinx.android.synthetic.main.data_activity.*


class DataBindingActivity : AppCompatActivity() {
    private var pageNo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DataActivityBinding = DataBindingUtil.setContentView(this, R.layout.data_activity)
        val mData = MyData()
        binding.setVariable(BR.mData, mData)
        binding.executePendingBindings()
        val intent: Intent = getIntent()
        pageNo = intent.getStringExtra(Contants.PAGE_NO)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setTitle(R.string.data_binding_str)

    }

    fun viewSourceCodeBtn(view: View) {
        NavigationController.navigateToMySourceCodeActivity(this, pageNo)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}