package com.hz.gank_kotlin.data.source.request

interface Request<out T> {

    fun request(): T
}