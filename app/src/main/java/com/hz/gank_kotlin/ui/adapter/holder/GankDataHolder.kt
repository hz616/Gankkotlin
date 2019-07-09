package com.hz.gank_kotlin.ui.adapter.holder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.ext.getDateString
import com.hz.gank_kotlin.ui.common.WebActivity
import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*
import org.jetbrains.anko.toast


class GankDataHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var viewBinding : ViewDataBinding

    constructor(viewBinding : ViewDataBinding) : this(viewBinding.root){
        this.viewBinding = viewBinding
    }

    private val onClickListener =  View.OnClickListener{
        val gank = it.tag as Gank
        WebActivity.start(it.context, gank.url)
    }

    fun setUp(gank: Gank){
        itemView.tv_desc.text = gank.desc
        itemView.tv_who.text = gank.who
        itemView.tv_date.text = gank.publishedAt.getDateString()
        itemView.item_wrapper.tag = gank
        itemView.item_wrapper.setOnClickListener(onClickListener)
    }
}