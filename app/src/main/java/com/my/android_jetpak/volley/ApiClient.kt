package com.my.android_jetpak.volley

import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.my.android_jetpak.listeners.MyResponse
import com.my.android_jetpak.utility.AndroidAppUtils
import com.my.android_jetpak.utility.MyApp.Companion.myApplication


/**
 * Created by darshna/2148 at 27/11/2018.
 */
class ApiClient
/**
 * class constructor
 *
 * @param context activity instance
 * @param res     JSONArray,JSONObject and string request response listener
 */
    (private val myResponse: MyResponse) {
    protected var message: String? = null
    private var responseType = 0

    /*Communication to Volley to get String response*/
    fun communicateVolleyStringRequest(url: String, responseType: Int) {
        AndroidAppUtils.showLog(TAG, "Communicate url: $url")
        this.responseType = responseType
        try {
            val queue = Volley.newRequestQueue(myApplication)
            val jsonArrayRequest = StringRequest(Request.Method.GET, url, Listener { response ->
                AndroidAppUtils.showLog(TAG, "Communicate url: $response")
                myResponse.onResponseObtained(response, responseType)
            }, ErrorListener { error ->
                AndroidAppUtils.showLog(TAG, "Communicate url: $error")
                myResponse.onErrorObtained(error.toString(), responseType)
            })
            jsonArrayRequest.setShouldCache(false)
            jsonArrayRequest.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )

            queue.add(jsonArrayRequest)
            AndroidAppUtils.showLog(TAG, "Communicate url: $url")
        } catch (e: Exception) {
            AndroidAppUtils.showLog(TAG, "Communicate: $e")
        }

    }

    companion object {
        private val TAG = "ApiClient "
    }
}
