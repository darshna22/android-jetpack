package com.my.android_jetpak.volley

import com.my.android_jetpak.listeners.MyResponse

/**
 * Created by darshna/2148 at 27/11/2018
 */
class ApiController
/**
 * API constructor
 *
 * @param context      Activity instance
 * @param response     Interface to get back response of API
 * @param responseType requested API response type
 */(private val response: MyResponse?, private val responseType: Int) : MyResponse {
    private var URL_COIN_DETAIL = "https://api.coingecko.com/api/v3/coins/"
    private var URL_GET_IMAGES = "https://pixabay.com/api/?"
    private val API_KEY = "8439361-5e1e53a0e1b58baa26ab398f7"


    private val client: ApiClient


    init {
        this.client = ApiClient(this)

    }

    /**
     * hit coin detail API
     */
    fun hitGetAllImagesApi(pageNo: Int?) {
        this.client.communicateVolleyStringRequest(getImageUrl(pageNo), this.responseType)
    }

    /**
     * Method use
     */
    private fun getImageUrl(pageNo: Int?): String {
        return URL_GET_IMAGES + "key=" + API_KEY + "&page=" + pageNo + "&per_page=3"

    }

    /**
     * hit coin detail API
     */
    fun hiCoinDetailApi(id: String?) {
        URL_COIN_DETAIL = URL_COIN_DETAIL + id
        this.client.communicateVolleyStringRequest(this.URL_COIN_DETAIL, this.responseType)
    }


    /**
     * hit coin list API
     */
    fun hiCoinListApi() {
        val URL_COIN_LIST = "https://api.coingecko.com/api/v3/coins/list"
        this.client.communicateVolleyStringRequest(URL_COIN_LIST, this.responseType)
    }

    /**
     * hit coin list API
     */
    fun hitCoinEventApi() {
        val URL_COIN_EVENT = "https://api.coingecko.com/api/v3/events"
        this.client.communicateVolleyStringRequest(URL_COIN_EVENT, this.responseType)
    }

    /**
     * hit coin price API
     */
    fun hitCoinPriceApi(id: String, toCurrency: String) {
        this.client.communicateVolleyStringRequest(getPriceUrl(id, toCurrency), this.responseType)
    }

    private fun getPriceUrl(id: String, toCurrency: String): String {
        return "https://api.coingecko.com/api/v3/simple/price?ids=$id&vs_currencies=$toCurrency"

    }


    /**
     * @param response     api  success response
     * @param responseType response type
     */
    override fun onResponseObtained(response: String, responseType: Int) {
        this.response?.onResponseObtained(response, responseType)
    }

    /**
     * @param errorMsg     api error response
     * @param responseType response type
     */
    override fun onErrorObtained(errorMsg: String, responseType: Int) {
        this.response?.onErrorObtained(errorMsg, responseType)
    }

    companion object {
        val COIN_LIST = 1
        val COIN_USD_PRICE = 2
        val COIN_INR_PRICE = 3
        val COIN_EVENT = 4
        val demoReq = 0
    }

}
