package com.hz.gank_kotlin.data.source.request

import com.google.gson.Gson
import com.hz.gank_kotlin.data.GankFilterResult
import java.net.URL

class GankFilterRequest(
    private val filter: String,
    private val page: Int,
    private val count: Int,
    private val gson: Gson = Gson()
) : Request<GankFilterResult?> {

    override fun request(): GankFilterResult? {
        val url = ServerConfig.gankDataFilterApi(filter, page, count)
        val jsonStr = URL(url).readText()
        try {
            return gson.fromJson(jsonStr, GankFilterResult::class.java)
        } catch (e: Exception) {

        }
        return null
    }

}