package com.hz.gank_kotlin.ui.home.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.ui.adapter.GankFilterAdapter
import com.hz.gank_kotlin.ui.adapter.LoadMoreListener
import com.hz.gank_kotlin.ui.adapter.holder.LoadingHolder
import kotlinx.android.synthetic.main.fragment_gank_filter.*

class GankFilterFragment : Fragment(), GankFilterContract.View {

    override lateinit var presenter: GankFilterContract.Presenter

    private var gankList: MutableList<Gank> = mutableListOf()
    private lateinit var gankFilterAdapter: GankFilterAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gank_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_gank_filter.layoutManager = LinearLayoutManager(activity)
        rv_gank_filter.setHasFixedSize(true)
        rv_gank_filter.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        gankFilterAdapter = GankFilterAdapter(gankList)
        rv_gank_filter.adapter = gankFilterAdapter

        gankFilterAdapter.mLoadMoreListener = object : LoadMoreListener{
            override fun loadMore() {
                presenter.loadMore()
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            refreshData()
        }

        rv_gank_filter.post {
            swipe_refresh_layout.isRefreshing = true
            refreshData()
        }
    }


    override fun onRefresh(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.clear()
        this.gankList.addAll(gankList)
        gankFilterAdapter.notifyDataSetChanged()
        refreshCompleted()
    }

    override fun onLoadMore(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.addAll(gankList)
        gankFilterAdapter.loadMoreCompleted()
        isDataEnd(isEnd)
        gankFilterAdapter.notifyDataSetChanged()
    }

    override fun refreshGankError() {
        refreshCompleted()
    }

    override fun loadMoreGankError() {
        gankFilterAdapter.loadMoreCompleted()
        gankFilterAdapter.mLoadingStatus = LoadingHolder.STATUS_FAILED
        gankFilterAdapter.notifyDataSetChanged()
    }

    private fun refreshData() {
        presenter.start()
    }

    private fun refreshCompleted() {
        rv_gank_filter?.post {
            swipe_refresh_layout?.isRefreshing = false
        }
    }

    private fun isDataEnd(isEnd: Boolean) {
        if (isEnd) {
            gankFilterAdapter.mLoadingStatus = LoadingHolder.STATUS_END
        } else {
            gankFilterAdapter.mLoadingStatus = LoadingHolder.STATUS_LOADING
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GankFilterFragment()
    }


}