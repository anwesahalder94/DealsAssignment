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

    //setting the presenter with the view
    init {
        dealsView.setPresenter(this)
    }

    /**
     * processGetDeals method is used to process the deals response
     */
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
}