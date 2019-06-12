package com.hz.gank_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.ui.GankDataItem
import com.hz.gank_kotlin.data.ui.GankHeaderItem
import com.hz.gank_kotlin.data.ui.GankItem
import com.hz.gank_kotlin.ui.adapter.holder.GankDataHolder
import kotlinx.android.synthetic.main.recycler_item_gank_header.view.*
import java.lang.IllegalArgumentException

class GankDailyAdapter constructor(private val gankItemList: MutableList<GankItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            GankItem.ITEM_HEADER -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_gank_header, parent, false)
                return GankHeaderHolder(itemView)
            }

            GankItem.ITEM_DATA -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_gank_data, parent, false)
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
                holder.setUp(gankItemList[position] as GankHeaderItem)
            }

            is GankDataHolder -> {
                holder.setUp((gankItemList[position] as GankDataItem).gank)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {

        return gankItemList[position].type
    }


    class GankHeaderHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setUp(gankHeaderItem: GankHeaderItem) {
            itemView.tv_header.text = gankHeaderItem.name
        }

    }
}