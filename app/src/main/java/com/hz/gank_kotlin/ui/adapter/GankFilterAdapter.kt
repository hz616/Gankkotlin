package com.hz.gank_kotlin.ui.adapter

import android.view.View
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.ext.getDateString
import com.hz.gank_kotlin.ui.common.WebActivity
import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*

class GankFilterAdapter(gankList: MutableList<Gank>) : BaseAdapter<Gank>(gankList, R.layout.recycler_item_gank_data) {


    override fun render(itemView: View, data: Gank) {
        itemView.tv_desc.text = data.desc
        itemView.tv_desc.text = data.desc
        itemView.tv_who.text = data.who
        itemView.tv_date.text = data.publishedAt.getDateString()
        itemView.item_wrapper.tag = data
        itemView.item_wrapper.setOnClickListener {

            WebActivity.start(it.context,data.url)

        }
    }
}