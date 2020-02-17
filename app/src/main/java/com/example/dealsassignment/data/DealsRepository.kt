package com.example.dealsassignment.data

import com.example.dealsassignment.data.model.DealsResponse

class DealsRepository(private val dealsRemoteDataSource: DealsDataSource) : DealsDataSource {

    override fun performGetTopDeals(
        sessionToken: String,
        fragmentName: String,
        getDealsCallBack: DataSourceCallBack<DealsResponse>
    ) {

        dealsRemoteDataSource.performGetTopDeals(
            sessionToken, fragmentName,
            object : DataSourceCallBack<DealsResponse> {
                override fun onError() {

                }

                override fun onSuccess(data: DealsResponse) {
                    getDealsCallBack.onSuccess(data)
                }
            })
    }
}