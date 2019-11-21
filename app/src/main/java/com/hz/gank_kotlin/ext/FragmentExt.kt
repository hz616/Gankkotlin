package com.hz.gank_kotlin.ext

import android.app.Application
import androidx.fragment.app.Fragment
import com.hz.gank_kotlin.ui.ViewModelFactory

/**
 * Create by hezhe
 * Date on 2019-11-13 11:47
 */

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory.getInstance(requireContext().applicationContext as Application)
}