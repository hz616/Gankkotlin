package com.hz.gank_kotlin.ui.home.filter

import com.hz.gank_kotlin.capture
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.data.source.GankFilterRepository
import com.hz.gank_kotlin.data.source.GankFilterSource
import org.junit.Before
import org.junit.Test
import org.mockito.*

/**
 * Create by hezhe
 * Date on 2019-11-12 11:33
 */
class GankFilterPresenterTest {
    @Mock
    private lateinit var gankFilterRepository: GankFilterRepository
    @Mock
    private lateinit var gankFilterView: GankFilterContract.View
    @Captor
    private lateinit var gankFilterRefreshCallback: ArgumentCaptor<GankFilterSource.LoadGankFilterCallback>
    @Captor
    private lateinit var gankFilterLoadMoreCallback: ArgumentCaptor<GankFilterSource.LoadGankFilterCallback>
    private lateinit var gankFilterPresenter: GankFilterPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gankFilterPresenter = GankFilterPresenter(gankFilterRepository, gankFilterView)

    }


    @Test
    fun refreshData_successShowMesssageUi() {
        val gankList: List<Gank> = ArrayList()
        val isEnd = false
        Mockito.`when`(
            gankFilterRepository.refreshGankList(
                ArgumentMatchers.anyString(),
                capture(gankFilterRefreshCallback)
            )
        ).thenAnswer {
            val loadGankFilterCallback: GankFilterSource.LoadGankFilterCallback = it.getArgument(1)
            loadGankFilterCallback.onGankFilterLoaded(gankList, isEnd)
        }
        gankFilterPresenter.refresh()
        Mockito.verify(gankFilterView).onRefresh(gankList, isEnd)

    }

    @Test
    fun refreshData_failStopRefresh() {
        Mockito.`when`(
            gankFilterRepository.refreshGankList(
                ArgumentMatchers.anyString(),
                capture(gankFilterRefreshCallback)
            )
        ).thenAnswer {
            val loadGankFilterCallback: GankFilterSource.LoadGankFilterCallback = it.getArgument(1)
            loadGankFilterCallback.onDataNotAvailable()
        }
        gankFilterPresenter.refresh()
        Mockito.verify(gankFilterView).refreshGankError()
    }

    @Test
    fun loadMoreData_successShowMoreMessage() {
        val gankList: List<Gank> = ArrayList()
        val isEnd = true
        Mockito.`when`(
            gankFilterRepository.loadMoreGankList(
                ArgumentMatchers.anyString(),
                capture(gankFilterRefreshCallback)
            )
        ).thenAnswer {
            val loadGankFilterCallback: GankFilterSource.LoadGankFilterCallback = it.getArgument(1)
            loadGankFilterCallback.onGankFilterLoaded(gankList, isEnd)
        }
        gankFilterPresenter.loadMore()
        Mockito.verify(gankFilterView).onLoadMore(gankList, isEnd)
    }

    @Test
    fun loadMoreData_failStopLoadMore() {
        Mockito.`when`(
            gankFilterRepository.loadMoreGankList(
                ArgumentMatchers.anyString(),
                capture(gankFilterRefreshCallback)
            )
        ).thenAnswer {
            val loadGankFilterCallback: GankFilterSource.LoadGankFilterCallback = it.getArgument(1)
            loadGankFilterCallback.onDataNotAvailable()
        }
        gankFilterPresenter.loadMore()
        Mockito.verify(gankFilterView).loadMoreGankError()
    }


}