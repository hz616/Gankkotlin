package com.hz.gank_kotlin.data.source

import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.data.source.local.GankDailyLocalSource
import com.hz.gank_kotlin.data.source.remote.GankDailyRemoteSource

class GankDailyRepository private constructor(
    private val gankDailyRemoteSource: GankDailyRemoteSource,
    private val gankDailyLocalSource: GankDailyLocalSource
) : GankDailySource {

    private var cacheDailyData: GankDailyData? = null
    private var cacheIsDirty = false

    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {

        if (cacheDailyData == null) {
            getLocalGankDaily(callback)
        } else {
            if (!cacheIsDirty) {
                cacheDailyData?.let {
                    callback.onGankLoaded(it)
                    return
                }
            }
        }

        if (cacheIsDirty) {
            getRemoteGankDaily(callback)
        } else {
            getLocalGankDaily(callback)
        }

    }


    private fun getLocalGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyLocalSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
                refreshCache(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    private fun getRemoteGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyRemoteSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
                refreshCache(gankDailyData)
                saveGankDaily(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    private fun refreshCache(gankDailyData: GankDailyData) {
        cacheDailyData = gankDailyData
        cacheIsDirty = false
    }

    override fun refreshGank() {
        cacheIsDirty = true
    }

    override fun deleteGankDaily() {
        gankDailyLocalSource.deleteGankDaily()
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
        gankDailyLocalSource.saveGankDaily(gankDailyData)
    }

    companion object {
        private var INSTANCE: GankDailyRepository? = null

        @JvmStatic
        fun getInstance(gankDailyLocalSource: GankDailyLocalSource,gankDailyRemoteSource: GankDailyRemoteSource):GankDailyRepository{
            return INSTANCE?:GankDailyRepository(gankDailyRemoteSource,gankDailyLocalSource).apply {
                INSTANCE = this
            }
        }
    }

}