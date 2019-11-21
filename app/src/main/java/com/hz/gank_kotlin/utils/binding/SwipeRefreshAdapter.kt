package com.hz.gank_kotlin.utils.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Create by hezhe
 * Date on 2019-11-21 11:52
 */


@BindingAdapter(
    value = ["android:isRefreshing", "android:onRefreshListener"],
    requireAll = true
)
fun setRefreshingAndListener(
    swipeRefreshLayout: SwipeRefreshLayout,
    isRefreshing: Boolean,
    onRefreshListener: SwipeRefreshLayout.OnRefreshListener
) {
    swipeRefreshLayout.setRefreshing(isRefreshing)
    swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
}