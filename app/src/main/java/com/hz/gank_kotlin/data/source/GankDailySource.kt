package com.hz.gank_kotlin.data.source

import com.hz.gank_kotlin.data.GankDailyData

interface GankDailySource {

    interface LoadGankCallback {

        fun onGankLoaded(gankDailyData: GankDailyData)

        fun onDataNotAvailable()
    }

    fun gankDaily(callback: LoadGankCallback)

    fun deleteGankDaily()

    fun saveGankDaily(gankDailyData: GankDailyData)

}