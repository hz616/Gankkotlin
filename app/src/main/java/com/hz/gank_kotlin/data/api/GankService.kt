package com.hz.gank_kotlin.data.api

import com.hz.gank_kotlin.data.GankDailyData
import com.hz.gank_kotlin.data.GankFilterResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {


    @GET("today")
    fun gankDaily(): Call<GankDailyData>


    @GET("data/{filterType}/{count}/{page}")
    fun gankFilter(@Path("filterType") filterType: String,
                   @Path("count") count: Int,
                   @Path("page") page: Int): Call<GankFilterResult>


    @GET("search/query/{queryText}/category/all/count/{count}/page/{page}")
    fun search(
        @Path("queryText") queryText: String,
        @Path("count") count: Int,
        @Path("page") page: Int
    ): Call<GankFilterResult>

}