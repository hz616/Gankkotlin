package com.hz.gank_kotlin.ui.home.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.ui.adapter.LoadMoreListener
import com.hz.gank_kotlin.ui.adapter.WelfareAdapter
import com.hz.gank_kotlin.ui.adapter.holder.LoadingHolder
import kotlinx.android.synthetic.main.fragment_welfare.*

class WelfareFragment : Fragment(), GankFilterContract.View {


    override lateinit var presenter: GankFilterContract.Presenter

    private var gankList: MutableList<Gank> = mutableListOf()

    private lateinit var welfareAdapter: WelfareAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welfare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welfareAdapter = WelfareAdapter(gankList)
        rv_welfare.layoutManager = GridLayoutManager(context, 2)
        rv_welfare.setHasFixedSize(true)
        rv_welfare.adapter = welfareAdapter

        welfareAdapter.mLoadMoreListener = object : LoadMoreListener {
            override fun loadMore() {
                presenter.loadMore()
            }
        }


        swipe_refresh_layout.setOnRefreshListener {
            presenter.refresh()
        }

        swipe_refresh_layout.isRefreshing = true
        presenter.start()


    }


    override fun onRefresh(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.clear()
        this.gankList.addAll(gankList)
        welfareAdapter.notifyDataSetChanged()
        refreshCompleted()
    }

    override fun onLoadMore(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.addAll(gankList)
        welfareAdapter.loadMoreCompleted()
        isDataEnd(isEnd)
        welfareAdapter.notifyDataSetChanged()

    }

    override fun refreshGankError() {
        refreshCompleted()
    }

    override fun loadMoreGankError() {
        welfareAdapter.loadMoreCompleted()
        welfareAdapter.mLoadingStatus = LoadingHolder.STATUS_FAILED
        welfareAdapter.notifyDataSetChanged()
    }


    private fun isDataEnd(isEnd: Boolean) {
        if (isEnd) {
            welfareAdapter.mLoadingStatus = LoadingHolder.STATUS_END
        } else {
            welfareAdapter.mLoadingStatus = LoadingHolder.STATUS_LOADING
        }
    }

    private fun refreshCompleted() {

        swipe_refresh_layout?.post {
            swipe_refresh_layout.isRefreshing = false
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelfareFragment()
    }


}