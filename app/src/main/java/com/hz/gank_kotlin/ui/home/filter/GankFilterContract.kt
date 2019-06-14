package com.hz.gank_kotlin.ui.home.filter

import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.ui.BasePresenter
import com.hz.gank_kotlin.ui.BaseView

interface GankFilterContract {

    interface View : BaseView<Presenter> {
        fun onRefresh(gankList: List<Gank>, isEnd: Boolean)

        fun onLoadMore(gankList: List<Gank>, isEnd: Boolean)

        fun refreshGankError()

        fun loadMoreGankError()


    }

    interface Presenter : BasePresenter {

        var currentFiltering: String
        fun refresh()
        fun loadMore()
    }
}