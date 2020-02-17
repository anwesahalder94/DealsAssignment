package com.example.dealsassignment.data

import com.example.dealsassignment.data.model.DealsResponse

interface DealsDataSource {
    fun performGetTopDeals(sessionToken: String, fragmentName: String, getDealsCallBack: DataSourceCallBack<DealsResponse>)
}