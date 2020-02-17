package com.example.dealsassignment.data

interface DataSourceCallBack<T> {
    fun onSuccess(data: T)
    fun onError()
}