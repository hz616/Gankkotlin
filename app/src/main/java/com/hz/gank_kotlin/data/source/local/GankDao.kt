package com.hz.gank_kotlin.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.ext.getDateString
import java.util.*


@Dao
interface GankDao {


    @Query("select * from daily where _date = :date")
    fun loadDaily(date:String = Date().getDateString()) : GankDailyData?


    @Query("delete from daily")
    fun deleteDaily()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(daily: GankDailyData)
}