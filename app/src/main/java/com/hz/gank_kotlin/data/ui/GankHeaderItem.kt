package com.hz.gank_kotlin.data.ui

data class GankHeaderItem(val name :String) : GankItem() {

    init {
        type = GankItem.ITEM_HEADER
    }
}