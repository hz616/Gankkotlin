package com.hz.gank_kotlin.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hz.gank_kotlin.Injection
import com.hz.gank_kotlin.data.source.GankDailyRepository
import com.hz.gank_kotlin.data.source.GankFilterRepository
import com.hz.gank_kotlin.ui.home.daily.GankDailyViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(
    private val gankDailyRepository: GankDailyRepository,
    private val gankFilterRepository: GankFilterRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T =

        with(modelClass) {
            when {
                isAssignableFrom(GankDailyViewModel::class.java) -> {
                    GankDailyViewModel(gankDailyRepository)
                }

                else -> throw IllegalArgumentException("unknown viewModel")
            }

        } as T


    companion object {
        private val INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) = INSTANCE ?: synchronized(ViewModelFactory::class.java) {
            INSTANCE ?: ViewModelFactory(
                Injection.provideGankRepository(application),
                Injection.provideGankFilterRepository()
            )
        }


    }

}