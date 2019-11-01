package com.my.android_jetpak.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.my.android_jetpak.R
import com.my.android_jetpak.volley.ApiController
import com.my.android_jetpak.listeners.MyResponse
import com.my.android_jetpak.model.EventData
import com.my.android_jetpak.model.EventResponse
import com.my.android_jetpak.room.EventRepository
import com.my.android_jetpak.utility.AndroidAppUtils
import com.my.android_jetpak.utility.AndroidAppUtils.showToast
import com.my.android_jetpak.utility.Contants
import com.my.android_jetpak.utility.MyApp
import com.my.android_jetpak.utility.NavigationController
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_fragment_main.*
import kotlinx.android.synthetic.main.event_fragment_main.toolbar
import java.util.concurrent.ExecutionException

class RoomEventActivity : AppCompatActivity(), MyResponse {
    private var mActivity: Activity? = null
    private var mDataRefresh: Boolean = false
    private var eventArrayList: List<EventData>? = null
    private var eventRepository: EventRepository? = null
    private var mAdapter: EventListAdapter? = null
    private var pageNo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_fragment_main)
        val intent: Intent = getIntent()
        pageNo = intent.getStringExtra(Contants.PAGE_NO)
        mActivity = this
        eventRepository = EventRepository(MyApp.myApplication)
        initViews()
        getOrHit()
    }


    @SuppressLint("NewApi")
    private fun initViews() {
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setTitle(R.string.event_str)
        mAdapter = EventListAdapter()
        val mLayoutManager = LinearLayoutManager(mActivity)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        pullToRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            if (!AndroidAppUtils.isNetworkConnectionAvailable(mActivity))
                showToast(mActivity, resources.getString(R.string.check_internet))
            else {
                mDataRefresh = true
            }
            pullToRefresh.setRefreshing(false)
        })
    }

    private fun getOrHit() {
        try {
            eventArrayList = eventRepository!!.allEventListData
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        if (eventArrayList != null && eventArrayList!!.size > 0) {
            println("RoomEventActivity.onCreate  " + eventArrayList + ":" + eventArrayList!!.size)
            updateAdapter(eventArrayList!!)
            data_not_found?.setVisibility(View.GONE)
        } else
            getEventData()
    }

    /**
     * Method use to update coin list adapter
     *
     * @param coinsArrayList coin list arrayList.
     */
    private fun updateAdapter(eventArrayList: List<EventData>) {
        mAdapter!!.addList(eventArrayList, this, pageNo)
        mAdapter!!.notifyDataSetChanged()
    }

    private fun getEventData() {
        AndroidAppUtils.showProgressDialog(
            mActivity,
            resources.getString(R.string.please_wait),
            true
        )
        ApiController(this, ApiController.COIN_EVENT)
            .hitCoinEventApi()

    }


    override fun onErrorObtained(error: String, requestNo: Int) {
        AndroidAppUtils.hideProgressDialog()

    }

    override fun onResponseObtained(response: String, requestNo: Int) {
        AndroidAppUtils.hideProgressDialog()
        println("list data here=" + response)
        val eventResponse = Gson().fromJson<EventResponse>(response, EventResponse::class.java)
        if (eventResponse != null && eventResponse.data != null && !eventResponse.data!!.isEmpty()) {
//            eventRepository!!.deleteAll()
            eventRepository!!.insertAll(eventResponse.data!!)
            eventArrayList = eventRepository!!.allEventListData


            println("CoinsListActivity.onCreate 11  " + eventArrayList + ":" + eventArrayList!!.size)

            updateAdapter(eventResponse.data as MutableList<EventData>)
        }
        data_not_found?.visibility = View.GONE
    }


    class EventListAdapter// RecyclerView recyclerView;
    internal constructor() : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {
        private var mEventDataList: List<EventData>? = null
        private lateinit var activity: Activity
        private var pageNo = ""
        internal fun addList(datumList: List<EventData>, activity: Activity, pageNo: String) {
            this.mEventDataList = datumList
            this.pageNo = pageNo
            this.activity = activity
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val listItem =
                LayoutInflater.from(parent.context).inflate(R.layout.event_list, parent, false)
            return ViewHolder(listItem)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (mEventDataList != null) {
                val datum = mEventDataList!![position]
                if (AndroidAppUtils.isEmptyStr(datum.type))
                    holder.type.visibility = View.GONE
                else
                    holder.type.setText(datum.type)

                if (AndroidAppUtils.isEmptyStr(datum.title))
                    holder.title.visibility = View.GONE
                else
                    holder.title.setText(datum.title)

                if (AndroidAppUtils.isEmptyStr(datum.organizer))
                    holder.organizer.visibility = View.GONE
                else
                    holder.organizer.setText(datum.organizer)

                if (AndroidAppUtils.isEmptyStr(datum.startDate))
                    holder.ll_startDate.visibility = View.GONE
                else
                    holder.startDate.setText(datum.startDate)

                if (AndroidAppUtils.isEmptyStr(datum.endDate))
                    holder.ll_endDate.visibility = View.GONE
                else
                    holder.endDate.setText(datum.endDate)

                if (AndroidAppUtils.isEmptyStr(datum.email))
                    holder.ll_email.visibility = View.GONE
                else
                    holder.email.setText(datum.email)

                if (AndroidAppUtils.isEmptyStr(datum.address))
                    holder.ll_address.visibility = View.GONE
                else
                    holder.address.setText(datum.address)


                if (AndroidAppUtils.isEmptyStr(datum.city))
                    holder.ll_city.visibility = View.GONE
                else
                    holder.city.setText(datum.city)

                if (AndroidAppUtils.isEmptyStr(datum.country))
                    holder.ll_country.visibility = View.GONE
                else
                    holder.country.setText(datum.country)

                if (AndroidAppUtils.isEmptyStr(datum.venue))
                    holder.ll_venue.visibility = View.GONE
                else
                    holder.venue.setText(datum.venue)

                if (AndroidAppUtils.isEmptyStr(datum.description))
                    holder.ll_description.visibility = View.GONE
                else
                    holder.description.setText(datum.description)

                //Loading Image from URL
                Picasso.get()
                    .load(datum.screenshot)
                    .placeholder(R.drawable.progress_animation)   // optional
                    .error(R.drawable.gradient_color)      // optional
                    .resize(300, 800)                        // optional
                    .into(holder.locationImage)

                holder.viewSourceCodeBtn.setOnClickListener {
                    NavigationController.navigateToMySourceCodeActivity(activity, "2")
                }

            }
        }


        override fun getItemCount(): Int {
            var size: Int = 0
            if (mEventDataList != null)
                size = mEventDataList!!.size
            return size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var type: TextView
            var title: TextView
            var organizer: TextView
            var startDate: TextView
            var endDate: TextView
            var email: TextView
            var address: TextView
            var city: TextView
            var country: TextView
            var description: TextView
            var venue: TextView
            var viewSourceCodeBtn: TextView

            var ll_startDate: LinearLayout
            var ll_endDate: LinearLayout
            var ll_email: LinearLayout
            var ll_city: LinearLayout
            var ll_country: LinearLayout
            var ll_address: LinearLayout
            var ll_description: LinearLayout
            var ll_venue: LinearLayout
            var locationImage: ImageView

            init {
                locationImage = itemView.findViewById(R.id.locationImage) as ImageView
                type = itemView.findViewById(R.id.type) as TextView
                title = itemView.findViewById(R.id.title) as TextView
                organizer = itemView.findViewById(R.id.organizer) as TextView
                startDate = itemView.findViewById(R.id.startDate) as TextView
                endDate = itemView.findViewById(R.id.endDate) as TextView
                email = itemView.findViewById(R.id.email) as TextView
                address = itemView.findViewById(R.id.address) as TextView
                city = itemView.findViewById(R.id.city) as TextView
                country = itemView.findViewById(R.id.country) as TextView
                description = itemView.findViewById(R.id.description) as TextView
                venue = itemView.findViewById(R.id.venue) as TextView

                ll_startDate = itemView.findViewById(R.id.ll_startDate) as LinearLayout
                ll_endDate = itemView.findViewById(R.id.ll_endDate) as LinearLayout
                ll_email = itemView.findViewById(R.id.ll_email) as LinearLayout
                ll_address = itemView.findViewById(R.id.ll_address) as LinearLayout
                ll_city = itemView.findViewById(R.id.ll_city) as LinearLayout
                ll_country = itemView.findViewById(R.id.ll_country) as LinearLayout
                ll_description = itemView.findViewById(R.id.ll_description) as LinearLayout
                ll_venue = itemView.findViewById(R.id.ll_venue) as LinearLayout
                viewSourceCodeBtn = itemView.findViewById(R.id.viewSourceCodeBtn) as TextView
            }
        }
    }

    fun viewSourceCodeBtn(view: View) {
        NavigationController.navigateToMySourceCodeActivity(this, pageNo)

    }

}