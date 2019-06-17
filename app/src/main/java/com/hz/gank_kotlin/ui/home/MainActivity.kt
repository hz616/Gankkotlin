package com.hz.gank_kotlin.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hz.gank_kotlin.Injection
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.ext.replaceFragmentInActivity
import com.hz.gank_kotlin.ext.setupToolBar
import com.hz.gank_kotlin.ext.transparentStatusBar
import com.hz.gank_kotlin.ui.home.daily.GankDailyFragment
import com.hz.gank_kotlin.ui.home.daily.GankDailyPresenter
import com.hz.gank_kotlin.ui.home.filter.GankFilterFragment
import com.hz.gank_kotlin.ui.home.filter.GankFilterPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var gankDailyPresenter: GankDailyPresenter
    private lateinit var gankFilterPresenter: GankFilterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolBar(toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        setupDrawerLayout()

        val gankDailyFragment = GankDailyFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame, GankDailyFragment.TAG)
        }

        gankDailyPresenter = GankDailyPresenter(
            Injection.provideGankRepository(applicationContext), gankDailyFragment
        )

        gankFilterPresenter = GankFilterPresenter(Injection.provideGankFilterRepository(), null)

    }

    private fun setupDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = Color.BLACK
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener { menuItem ->
            title = menuItem.title
            drawer_layout.closeDrawers()
            when (menuItem.itemId) {
                R.id.menu_today -> {
                    if (null == supportFragmentManager.findFragmentByTag(GankDailyFragment.TAG)) {
                        GankDailyFragment.newInstance().also {
                            gankDailyPresenter.gankDailyView = it
                            it.presenter = gankDailyPresenter
                            replaceFragmentInActivity(it, R.id.contentFrame, GankDailyFragment.TAG)
                        }
                    }
                }
                R.id.menu_android -> {
                    filterChange(GankFilterType.ANDROID)
                }
                R.id.menu_ios -> {
                    filterChange(GankFilterType.IOS)
                }
                R.id.menu_web -> {
                    filterChange(GankFilterType.WEB)
                }
                R.id.menu_app -> {
                    filterChange(GankFilterType.APP)
                }
                R.id.menu_extra -> {
                    filterChange(GankFilterType.EXTRA_SOURCES)
                }
//                R.id.menu_video -> {
//                    filterChange(GankFilterType.VIDEO)
//                }
            }
            return@setNavigationItemSelectedListener true
        }

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(R.drawable.seb5)
            .into(nav_view.getHeaderView(0).iv_nav_header)
    }

    private fun filterChange(filterType: String) {
        val gankFilterFragment = supportFragmentManager.findFragmentByTag(filterType)
        if (null == gankFilterFragment) {
            GankFilterFragment.newInstance().also {
                gankFilterPresenter.gankFilterView = it
                it.presenter = gankFilterPresenter
                gankFilterPresenter.currentFiltering = filterType
                replaceFragmentInActivity(it, R.id.contentFrame, filterType)
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}
