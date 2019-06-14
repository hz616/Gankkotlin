package com.hz.gank_kotlin.data.source

import com.hz.gank_kotlin.data.Gank


interface GankFilterSource {

    interface LoadGankFilterCallback {

        fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean)

        fun onDataNotAvailable()
    }

    fun gankFilter(filter: String, page: Int, count: Int, callback: LoadGankFilterCallback)

    fun refreshGankList(currentFiltering: String, callback: LoadGankFilterCallback)

    fun loadMoreGankList(currentFiltering: String, callback: LoadGankFilterCallback)


}