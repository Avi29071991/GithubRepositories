package com.avinash.test.githubtrendingrepos.domain

import androidx.lifecycle.MutableLiveData

abstract class UseCase<T: Any> {

    val liveData =  MutableLiveData<T>()

    private val dataMap: MutableMap<String, Any> = mutableMapOf()

    fun setData(key: String, value: Any) {
        dataMap[key] = value
    }

    fun fetchData(key: String): Any? {
        return dataMap[key]
    }

    abstract suspend fun execute()
}