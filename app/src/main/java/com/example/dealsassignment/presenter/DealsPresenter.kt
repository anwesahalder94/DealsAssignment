package com.example.dealsassignment.presenter

import com.example.dealsassignment.data.DataSourceCallBack
import com.example.dealsassignment.data.DealsDataSource
import com.example.dealsassignment.data.model.Datum
import com.example.dealsassignment.data.model.DealsResponse
import com.example.dealsassignment.util.Constants
import com.example.dealsassignment.view.DealsContract
import java.util.ArrayList

class DealsPresenter(
    private val dealsRepository: DealsDataSource,
    val dealsView: DealsContract.DealsView
) : DealsContract.DealsPresenter {

    init {
        dealsView.setPresenter(this)
    }

    override fun processGetDeals(sessionToken: String, fragmentName: String) {

        dealsRepository.performGetTopDeals(
            sessionToken,
            fragmentName,
            object : DataSourceCallBack<DealsResponse> {
                override fun onError() {
                    dealsView.onError()
                }

                override fun onSuccess(data: DealsResponse) {
                    dealsView.onDealsSuccess(data)
                }
            })
    }

    override fun getDealsList(dealsListPerPage: List<Datum>): MutableList<Datum> {
        val dealsList = ArrayList<Datum>()
        for (response in dealsListPerPage) {
            if (response != null) {
                dealsList.add(response)
            }
        }
        return dealsList
    }
}