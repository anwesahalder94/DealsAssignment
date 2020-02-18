package com.example.dealsassignment.view

import com.example.dealsassignment.BaseContract
import com.example.dealsassignment.data.model.Datum
import com.example.dealsassignment.data.model.DealsResponse

interface DealsContract {

    interface DealsView: BaseContract.View<DealsPresenter>{
        fun onDealsSuccess(dealsResponse: DealsResponse)
        fun onError()
    }

    interface DealsPresenter : BaseContract.Presenter{
        fun processGetDeals(sessionToken: String, fragmentName: String)
    }
}