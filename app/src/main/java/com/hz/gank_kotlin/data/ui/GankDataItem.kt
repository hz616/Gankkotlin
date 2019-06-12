package com.hz.gank_kotlin.data.ui

import com.hz.gank_kotlin.data.Gank

data class GankDataItem(val gank: Gank) : GankItem() {
    init {
        type = GankItem.ITEM_DATA
    }
}