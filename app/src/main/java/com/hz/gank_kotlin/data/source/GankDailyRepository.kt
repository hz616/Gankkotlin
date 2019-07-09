package com.hz.gank_kotlin.data.source

import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.data.source.local.GankDailyLocalSource
import com.hz.gank_kotlin.data.source.remote.GankDailyRemoteSource

class GankDailyRepository private constructor(
    private val gankDailyRemoteSource: GankDailyRemoteSource,
    private val gankDailyLocalSource: GankDailyLocalSource
) : GankDailySource {

    private var firstLoad = true

    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {

        if (firstLoad) {
            getRemoteGankDaily(callback)
            firstLoad = false
        }
        getLocalGankDaily(callback)
    }


    private fun getLocalGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyLocalSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
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
                saveGankDaily(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
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
        fun getInstance(
            gankDailyLocalSource: GankDailyLocalSource,
            gankDailyRemoteSource: GankDailyRemoteSource
        ): GankDailyRepository {
            return INSTANCE ?: GankDailyRepository(gankDailyRemoteSource, gankDailyLocalSource).apply {
                INSTANCE = this
            }
        }
    }

}