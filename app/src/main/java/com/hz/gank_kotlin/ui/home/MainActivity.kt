package com.hz.gank_kotlin.ui.home

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.ext.setupToolBar
import com.hz.gank_kotlin.ext.transpartStatusBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transpartStatusBar()
        setupToolBar(toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        setupDrawerLayout()
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
                    toast("today click")
                }
            }
            return@setNavigationItemSelectedListener true
        }

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(R.drawable.seb5)
            .into(nav_view.getHeaderView(0).iv_nav_header)
    }


}