package com.my.android_jetpak.utility

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import com.my.android_jetpak.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects


/**
 * Utils of this application use static methods of application.
 *
 * @author Darshna/2148
 */
object AndroidAppUtils {
    private val TAG = "AndroidAppUtils"

    /**
     * Object instance of progress bar
     */
    var ISDEBUGGING = true
    private var mProgressDialog: ProgressDialog? = null

    /**
     * Show debug Message into logcat
     *
     * @param TAG
     * @param msg
     */
    fun showLog(TAG: String, msg: String) {
        if (ISDEBUGGING) {
            try {
                Log.d(TAG, msg)
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        }

    }


    /**
     * Show debug Error Message into logcat
     *
     * @param TAG
     * @param msg
     */
    fun showErrorLog(TAG: String, msg: String) {
        if (ISDEBUGGING) {
            Log.e(TAG, msg)
        }

    }


    /**
     * Show Toast
     *
     * @param mActivity activity instance
     * @param msg       message to show
     */
    fun showToast(mActivity: Activity?, msg: String?) {
        try {
            if (mActivity != null && msg != null && msg.length > 0) {
                mActivity.runOnUiThread {
                    Toast.makeText(mActivity, msg, Toast.LENGTH_LONG)
                        .show()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    /**
     * Showing progress dialog
     *
     * @param msg message to show progress dialog
     */
    fun showProgressDialog(
        mActivity: Activity?,
        msg: String, isCancelable: Boolean
    ) {
        try {
            if (mActivity != null && mProgressDialog != null
                && mProgressDialog!!.isShowing
            ) {
                try {
                    mProgressDialog!!.dismiss()
                    mProgressDialog = null
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            mProgressDialog = null
            if (mProgressDialog == null && mActivity != null) {
                mActivity.runOnUiThread {
                    mProgressDialog = ProgressDialog(mActivity, R.style.MyAlertDialogStyle)
                    mProgressDialog!!.setMessage(msg)
                    mProgressDialog!!.setCancelable(isCancelable)
                }

            }
            if (mActivity != null && mProgressDialog != null
                && !mProgressDialog!!.isShowing
            ) {
                mProgressDialog!!.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Hide progress dialog
     */
    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            try {
                mProgressDialog!!.dismiss()
                mProgressDialog = null

            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        } else {
            showErrorLog(TAG, "mProgressDialog is null")
        }
    }


    /**
     * check string
     *
     * @param string input string
     * @return true/false
     */
    fun isEmptyStr(string: String?): Boolean {
        return string == null || string.trim { it <= ' ' }.isEmpty()
    }


    /**
     * check network Connection Available if not then its return
     * true/false value and show network connectivity dialog
     *
     * @param activity activity instance
     * @return true/false
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun isNetworkConnectionAvailable(activity: Activity?): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = Objects.requireNonNull(cm).activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected
        if (isConnected) {
            AndroidAppUtils.showLog("Network", "Connected")
            return true
        } else {
            //            checkNetworkConnection(activity);
            AndroidAppUtils.showLog("Network", "Not Connected")
            return false
        }
    }

    /**
     * Convert JSON date to specific date format
     *
     * @param myStrDate json date
     */
    fun convertStringDateToTime(myStrDate: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        var date: Date? = null
        try {
            date = dateFormat.parse(myStrDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        assert(date != null)
        return date!!.time
    }

    /**
     * Convert JSON date to time
     *
     * @param jsonDate json date
     */
    fun convertJSONDateToTime(jsonDate: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        var date: Date? = null
        try {
            date = dateFormat.parse(jsonDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val targetFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
        return targetFormat.format(date)
    }


}