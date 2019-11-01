package com.my.android_jetpak.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.my.android_jetpak.R
import com.my.android_jetpak.fragment.*
import com.my.android_jetpak.utility.Contants
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

import kotlinx.android.synthetic.main.source_code_activity.*

class MySourceCodeActivity : AppCompatActivity() {
    private var pageNo = ""
    private lateinit var adapter: FragmentPagerItemAdapter
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.source_code_activity)
        val intent: Intent = getIntent()
        pageNo = intent.getStringExtra(Contants.PAGE_NO)

        when (pageNo) {
            "0" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaDataBindingFragment::class.java)
                    .add(getString(R.string.xml), DataBindingXMLFragment::class.java)
                    .add(getString(R.string.extra), DataBindingExtrasFragment::class.java)
                    .create()
            )
            "1" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaTwoDataBindingFragment::class.java)
                    .add(getString(R.string.xml), TwoDataBindingXMLFragment::class.java)
                    .add(getString(R.string.extra), DataBindingExtrasFragment::class.java)
                    .create()
            )

            "2" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaRoomFragment::class.java)
                    .add(getString(R.string.xml), RoomXMLFragment::class.java)
                    .add(getString(R.string.extra), RoomExtrasFragment::class.java)
                    .create()
            )
            "3" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaBottomNavigationFragment::class.java)
                    .add(getString(R.string.xml), BottomNavigtionXMLFragment::class.java)
                    .add(getString(R.string.extra), BottomNavigationExtrasFragment::class.java)
                    .create()
            )
            "4" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaMVVMFragment::class.java)
                    .add(getString(R.string.xml), MVVMXMLFragment::class.java)
                    .add(getString(R.string.extra), MVVMExtrasFragment::class.java)
                    .create()
            )
            "5" -> adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                    .add(getString(R.string.java), JavaWorkManagerFragment::class.java)
                    .add(getString(R.string.extra), WorkManagerExtrasFragment::class.java)
                    .create()
            )
        }
        viewPager.adapter = adapter
        tabLayout.setViewPager(viewPager)

    }
}
