package com.hz.gank_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hz.gank_kotlin.BR
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.data.ui.GankDataItem
import com.hz.gank_kotlin.data.ui.GankHeaderItem
import com.hz.gank_kotlin.data.ui.GankItem
import com.hz.gank_kotlin.ui.adapter.holder.GankDataHolder
import kotlinx.android.synthetic.main.recycler_item_gank_header.view.*
import java.lang.IllegalArgumentException

open class GankDailyAdapter constructor(private val gankItemList: MutableList<GankItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun replaceItems(gankItemList: MutableList<GankItem>) {
        this.gankItemList.clear()
        this.gankItemList.addAll(gankItemList)
        notifyDataSetChanged()
    }

    open fun addListener(root: View, itemData: Gank, position: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            GankItem.ITEM_HEADER -> {
                val itemView = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_gank_header,
                    parent,
                    false
                ) as ViewDataBinding
                return GankHeaderHolder(itemView)
            }

            GankItem.ITEM_DATA -> {
                val itemView = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_gank_data,
                    parent,
                    false
                ) as ViewDataBinding
                return GankDataHolder(itemView)
            }

            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun getItemCount(): Int {
        return gankItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GankHeaderHolder -> {
                holder.viewBinding?.setVariable(BR.headerTitle, gankItemList[position] as GankHeaderItem)
            }

            is GankDataHolder -> {
                holder.viewBinding?.setVariable(BR.gankItem, (gankItemList[position] as GankDataItem).gank)
                addListener(holder.viewBinding.root, (gankItemList[position] as GankDataItem).gank, position)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {

        return gankItemList[position].type
    }


    class GankHeaderHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var viewBinding: ViewDataBinding

        constructor(viewBinding: ViewDataBinding) : this(viewBinding.root) {
            this.viewBinding = viewBinding
        }

    }
}