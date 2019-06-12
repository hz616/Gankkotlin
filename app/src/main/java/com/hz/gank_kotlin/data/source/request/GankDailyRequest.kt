package com.hz.gank_kotlin.data.source.request

import com.google.gson.Gson
import com.hz.gank_kotlin.data.GankDailyResult
import java.net.URL

class GankDailyRequest(private val gson: Gson = Gson()) : Request<GankDailyResult?> {

    override fun request(): GankDailyResult? {
        val url = ServerConfig.gankDailyApi
        val todayJsonStr = URL(url).readText()

        try {
            return gson.fromJson(todayJsonStr,GankDailyResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}