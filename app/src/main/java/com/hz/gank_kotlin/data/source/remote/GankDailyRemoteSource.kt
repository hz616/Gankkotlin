package com.hz.gank_kotlin.data.source.remote

import android.util.Log
import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.data.source.GankDailySource
import com.hz.gank_kotlin.data.source.request.GankDailyRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class GankDailyRemoteSource private constructor() : GankDailySource {

    private val TAG = javaClass.simpleName

    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {

        doAsync {
            val gankResult = GankDailyRequest().request()
            Log.i(TAG, "$gankResult")
            uiThread {
                if (gankResult == null) {
                    callback.onDataNotAvailable()
                } else {
                    if (gankResult.error) {
                        callback.onDataNotAvailable()
                    } else {
                        callback.onGankLoaded(gankResult.results)
                    }
                }
            }
        }

    }

    override fun refreshGank() {
    }

    override fun deleteGankDaily() {
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
    }


    companion object {
        private var INSTANCE: GankDailyRemoteSource? = null

        fun getInstance(): GankDailyRemoteSource {
            return INSTANCE ?: GankDailyRemoteSource().apply {
                INSTANCE = this
            }
        }
    }
}