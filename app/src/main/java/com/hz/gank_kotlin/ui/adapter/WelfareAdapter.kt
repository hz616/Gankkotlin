package com.hz.gank_kotlin.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import kotlinx.android.synthetic.main.recycler_item_welfare.view.*

class WelfareAdapter(val gankList: MutableList<Gank>) : BaseAdapter<Gank>(gankList, R.layout.recycler_item_welfare) {


    private val onClickListener = View.OnClickListener {
        if (gankList.isNullOrEmpty()) return@OnClickListener
        val gank = it.tag as Gank
        val position = gankList.indexOf(gank)

    }


    override fun render(itemView: View, data: Gank) {
        Glide
            .with(itemView.context)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(data.url)
            .into(itemView.iv_welfare)

        itemView.tag = data

    }


}