package com.hz.gank_kotlin

import android.content.Context
import com.hz.gank_kotlin.data.source.GankDailyRepository
import com.hz.gank_kotlin.data.source.local.GankDailyLocalSource
import com.hz.gank_kotlin.data.source.local.GankDataBase
import com.hz.gank_kotlin.data.source.remote.GankDailyRemoteSource
import com.hz.gank_kotlin.utils.AppExecutors


object Injection {


    fun provideGankRepository(context: Context): GankDailyRepository {
        val dataBase = GankDataBase.getInstance(context)
        return GankDailyRepository.getInstance(
            GankDailyLocalSource.getInstance(AppExecutors(), dataBase.gankDao()),
            GankDailyRemoteSource.getInstance()
        )
    }
}