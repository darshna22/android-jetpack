package com.my.android_jetpak.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.android_jetpak.BR
import com.my.android_jetpak.R
import com.my.android_jetpak.databinding.EventListBinding
import com.my.android_jetpak.model.EventData
import com.my.android_jetpak.mvvm.DataModelView
import com.my.android_jetpak.utility.Contants
import com.my.android_jetpak.utility.NavigationController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_activity.toolbar
import kotlinx.android.synthetic.main.mvvm_activity.data_not_found
import kotlinx.android.synthetic.main.mvvm_activity.recyclerView

class MVVMActivity : AppCompatActivity() {
    private var pageNo = ""
    private var mContext: Activity? = null
    private lateinit var mProgressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mvvm_activity)
        mContext = this@MVVMActivity
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog!!.setMessage(getString(R.string.str_progress_message))
        mProgressDialog.show()
        val intent: Intent = getIntent()
        pageNo = intent.getStringExtra(Contants.PAGE_NO)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setTitle(R.string.mvvm_str)


        val dataModelView: DataModelView =
            ViewModelProviders.of(mContext as MVVMActivity).get(DataModelView::class.java)
        dataModelView.getScratchData(mContext as MVVMActivity)
            .observe(this, object : Observer<List<EventData>> {
                override fun onChanged(t: List<EventData>?) {
                    print("size of list is:" + t!!.size)
                    val adapter = EventListAdapter()
                    adapter.addList(t, mContext as MVVMActivity, pageNo)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(mContext)
                    recyclerView.adapter = adapter
                    mProgressDialog.hide()
                    data_not_found?.visibility = View.GONE

                }

            })
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
            val layoutInflater = LayoutInflater.from(parent.getContext());
            val binding: EventListBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.event_list, parent, false);
            return ViewHolder(binding)
        }

        class ViewHolder(val binding: EventListBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(eventData: EventData) {
                binding.setVariable(BR.mEventData, eventData)
            }

        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(mEventDataList!!.get(position))
            if (mEventDataList != null) {
                val datum = mEventDataList!![position]

                //Loading Image from URL
                Picasso.get()
                    .load(datum.screenshot)
                    .placeholder(R.drawable.progress_animation)   // optional
                    .error(R.drawable.gradient_color)      // optional
                    .resize(300, 800)                        // optional
                    .into(holder.binding.locationImage)

                holder.binding.viewSourceCodeBtn.setOnClickListener {
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


    }

}