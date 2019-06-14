package com.hz.gank_kotlin.ui.home.filter

import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.data.source.GankFilterRepository
import com.hz.gank_kotlin.data.source.GankFilterSource
import com.hz.gank_kotlin.ui.home.GankFilterType

class GankFilterPresenter(
    private val gankFilterRepository: GankFilterRepository,
    var gankFilterView: GankFilterContract.View?
) : GankFilterContract.Presenter {

    override var currentFiltering: String =  GankFilterType.ANDROID

    override fun refresh() {
        gankFilterRepository.refreshGankList(currentFiltering, refreshCallback)
    }

    override fun loadMore() {
        gankFilterRepository.loadMoreGankList(currentFiltering, loadMoreCallback)
    }

    override fun start() {
        refresh()
    }


    private val refreshCallback = object : GankFilterSource.LoadGankFilterCallback {
        override fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean) {
            gankFilterView?.onRefresh(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            gankFilterView?.refreshGankError()
        }

    }

    private val loadMoreCallback = object : GankFilterSource.LoadGankFilterCallback {
        override fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean) {
            gankFilterView?.onLoadMore(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            gankFilterView?.loadMoreGankError()
        }

    }
}