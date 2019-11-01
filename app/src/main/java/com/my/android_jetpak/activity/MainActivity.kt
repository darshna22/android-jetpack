package com.my.android_jetpak.activity

import android.app.Activity
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.Toast
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.android_jetpak.jobScheduler.MyEventJobService_
import com.my.android_jetpak.utility.AndroidAppUtils
import com.my.android_jetpak.utility.Contants.Companion.BOTTOM_NAVIGATION
import com.my.android_jetpak.utility.Contants.Companion.DATA_BINDING
import com.my.android_jetpak.utility.Contants.Companion.MVVM
import com.my.android_jetpak.utility.Contants.Companion.ROOM
import com.my.android_jetpak.utility.Contants.Companion.TWO_WAY_DATA_BINDING
import com.my.android_jetpak.utility.Contants.Companion.WORK_MANAGER
import com.my.android_jetpak.R
import com.my.android_jetpak.utility.NavigationController


/**
 * Created by Darshna Kumari on 02,July,2019
 * darshnakumari95@outlook.com
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mActivity: Activity
    private val JOB_ID = 0x34
    private lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: Array<String> =
            arrayOf(DATA_BINDING, TWO_WAY_DATA_BINDING, ROOM, BOTTOM_NAVIGATION, MVVM, WORK_MANAGER)
        for (element in list)
            println("list element is: " + element)
        mActivity = this
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val adapter = MyTopicsAdapter(list.asList(), this)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
        startWorkManager()
    }


    private fun startWorkManager() {
        jobInfo = JobInfo.Builder(JOB_ID, ComponentName(this, MyEventJobService_::class.java))
//            .setRequiresCharging(true)
//            .setMinimumLatency(/*15 * 60 * 1000L*/10) //YOUR_TIME_INTERVAL
            .setPeriodic(900000L)
            .setRequiresDeviceIdle(false)
            .setPersisted(true)
            .setRequiresBatteryNotLow(false)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val result = jobScheduler.schedule(jobInfo);
        if (result == 1)
            print("Schedule Successfully")
        else
            print("Schedule Failed")
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_toolbar, menu)
//        return true
//    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

//            R.id.feedbackFragment -> {
//
//            }
            R.id.rateUsFragment -> {
                NavigationController.navigateToRateUs(mActivity)
            }
            R.id.shareFragment -> {
                NavigationController.shareApplication(mActivity)
            }
            R.id.websiteFragment ->{
                NavigationController.navigateToWebsite(mActivity)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    class MyTopicsAdapter(val itemList: List<String>, val mContext: Context) :
        RecyclerView.Adapter<MyTopicsAdapter.MyViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val listItem =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_raw, parent, false)
            return MyViewHolder(listItem)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val activity = mContext as Activity
            if (itemList.size > 0) {
                holder.textView.setText(itemList.get(position))
                holder.ll_parent_container.setOnClickListener {

                    if (itemList.get(position).equals(DATA_BINDING)) {
                        NavigationController.navigateToDataBindingActivity(activity)
                    } else if (itemList.get(position).equals(TWO_WAY_DATA_BINDING)) {
                        NavigationController.navigateToTwoWayDataBinding(activity)
                    } else if (itemList.get(position).equals(BOTTOM_NAVIGATION)) {
                        NavigationController.navigateToBottomNavigationActivity(activity)
                    } else if (itemList.get(position).equals(ROOM)) {
                        if (AndroidAppUtils.isNetworkConnectionAvailable(mContext))
                            NavigationController.navigateToRoomEventActivity(activity)
                        else
                            AndroidAppUtils.showToast(mContext,mContext.resources.getString(R.string.check_internet_connection))

                    } else if (itemList.get(position).equals(MVVM)) {
                        if (AndroidAppUtils.isNetworkConnectionAvailable(mContext))
                            NavigationController.navigateToMVVMActivity(activity, "4")
                        else
                            AndroidAppUtils.showToast(
                                mContext,
                                mContext.resources.getString(R.string.check_internet_connection)
                            )

                    }else if (itemList.get(position).equals(WORK_MANAGER)) {
                        NavigationController.navigateToMySourceCodeActivity(activity, "5")
                    }
                }

            }
        }

        override fun getItemCount(): Int {
            return itemList.size
        }


        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView
            var ll_parent_container: CardView

            init {
                this.textView =
                    itemView.findViewById(R.id.topicName) as TextView
                ll_parent_container =
                    itemView.findViewById(R.id.ll_parent_container) as CardView
            }
        }
    }


}
