package com.hz.gank_kotlin.ui.home.daily

import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.ui.BasePresenter
import com.hz.gank_kotlin.ui.BaseView

interface GankDailyContract {
    interface View : BaseView<Presenter> {
        fun setLoadingIndicator(active: Boolean)
        fun showGankDaily(gankDailyData: GankDailyData)
        fun showLoadingGankError()
    }

    interface Presenter : BasePresenter {
        fun gankDaily(forceUpdate: Boolean)
    }
}