package com.my.android_jetpak.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.my.android_jetpak.R
import com.my.android_jetpak.utility.NavigationController
import kotlinx.android.synthetic.main.contact_fragment.view.*

class NavContactFragment : Fragment() {
    lateinit var mView: View
    private var mActivity: android.app.Activity? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.contact_fragment, container, false)
        mView.viewSourceCodeBtn.setOnClickListener {
            mActivity = activity
            mActivity?.let { NavigationController.navigateToMySourceCodeActivity(it, "3") }
        }
        return mView
    }
}