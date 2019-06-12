package com.hz.gank_kotlin.ui.home.daily

import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.data.source.GankDailyRepository
import com.hz.gank_kotlin.data.source.GankDailySource

class GankDailyPresenter constructor(
    private val gankDailyRepository: GankDailyRepository,
    var gankDailyView: GankDailyContract.View
) : GankDailyContract.Presenter {


    private var firstLoad = true

    init {
        gankDailyView?.presenter = this
    }

    override fun gankDaily(forceUpdate: Boolean) {

        if (forceUpdate || firstLoad) {
            gankDailyRepository.refreshGank()
        }
        firstLoad = false
        gankDailyRepository.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
                gankDailyView?.showGankDaily(gankDailyData)
            }

            override fun onDataNotAvailable() {
                gankDailyView?.showLoadingGankError()
            }

        })

    }

    override fun start() {
        gankDaily(false)
    }


}