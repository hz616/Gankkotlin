package com.hz.gank_kotlin.data.source.local

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hz.gank_kotlin.data.Gank

class GankConvert {

    companion object {

        private val gson = Gson()


        @TypeConverter
        @JvmStatic
        fun toGankList(json: String): List<Gank> {
            if (TextUtils.isEmpty(json)) {
                return listOf()
            }

            return gson.fromJson(json, Array<Gank>::class.java).asList()
        }


        @TypeConverter
        @JvmStatic
        fun toGankJsonStr(gankList: List<Gank>): String {
            return gson.toJson(gankList)
        }

    }


}