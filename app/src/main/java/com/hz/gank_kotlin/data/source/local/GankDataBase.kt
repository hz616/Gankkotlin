package com.hz.gank_kotlin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hz.gank_kotlin.data.GankDailyData

@Database(entities = [GankDailyData::class], version = 1)
@TypeConverters(value = [GankConvert::class])
abstract class GankDataBase : RoomDatabase() {


    abstract fun gankDao(): GankDao


    companion object {
        private var INSTANCE: GankDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context):GankDataBase{
            synchronized(lock){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,
                        GankDataBase::class.java,
                        "hz_gank.db").build()
                }
                return INSTANCE!!
            }
        }
    }


}