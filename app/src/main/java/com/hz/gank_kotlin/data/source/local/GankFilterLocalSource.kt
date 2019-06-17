package com.hz.gank_kotlin.data.source.local

import com.hz.gank_kotlin.data.source.GankFilterSource


class GankFilterLocalSource : GankFilterSource {
    override fun loadMoreGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    override fun refreshGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    override fun gankFilter(filter: String, page: Int, count: Int, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    companion object {
        private var INSTANCE: GankFilterLocalSource? = null

        @JvmStatic
        fun getInstance(): GankFilterLocalSource {
            return INSTANCE ?: GankFilterLocalSource().apply {
                INSTANCE = this
            }
        }
    }

}