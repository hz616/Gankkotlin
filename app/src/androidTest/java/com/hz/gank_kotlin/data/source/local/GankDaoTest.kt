package com.hz.gank_kotlin.data.source.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hz.gank_kotlin.data.GankDailyData
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Create by hezhe
 * Date on 2019-11-12 14:57
 */

@RunWith(AndroidJUnit4::class)
class GankDaoTest {


    private lateinit var dataBase: GankDataBase

    @Before
    fun initDb() {
        //这里仅仅使用内存存储，这样进程被杀掉后，数据也随即删除掉
        dataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            GankDataBase::class.java
        ).build()
    }

    @After
    fun closeDb() = dataBase.close()


    @Test
    fun insertDataAndGetByTime() {
        val gankDailyData = GankDailyData()
        dataBase.gankDao().insert(gankDailyData)
        val loaded = dataBase.gankDao().loadDaily()
        Assert.assertNotNull(loaded)
    }


    @Test
    fun deleteDataAndGetByTime() {
        val gankDailyData = GankDailyData()
        dataBase.gankDao().insert(gankDailyData)
        dataBase.gankDao().deleteDaily()
        val loaded = dataBase.gankDao().loadDaily()
        Assert.assertNull(loaded)
    }


}