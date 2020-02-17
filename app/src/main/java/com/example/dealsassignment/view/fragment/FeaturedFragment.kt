package com.example.dealsassignment.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dealsassignment.R
import com.example.dealsassignment.data.DealsRepository
import com.example.dealsassignment.data.model.Datum
import com.example.dealsassignment.data.model.DealsResponse
import com.example.dealsassignment.data.remote.DealsRemoteDataSource
import com.example.dealsassignment.presenter.DealsPresenter
import com.example.dealsassignment.util.Constants
import com.example.dealsassignment.view.DealsContract
import com.example.dealsassignment.view.adapter.DealsAdapter
import kotlinx.android.synthetic.main.fragment_top.*

class FeaturedFragment: Fragment(), DealsContract.DealsView {

    private var mDealsAdapter: DealsAdapter? = null
    private lateinit var mDealsPresenter: DealsPresenter
    private lateinit var mFeaturedDealsResponse: DealsResponse
    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_featured, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setTitle("Please wait")

        mDealsPresenter = DealsPresenter(DealsRepository(DealsRemoteDataSource()), this)
        mDealsPresenter.processGetDeals(Constants.SESSION_TOKEN_VALUE, Constants.FEATURED_FRAGMENT)

        mProgressDialog.show()
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view?.setHasFixedSize(true)
        recycler_view?.layoutManager = layoutManager

        onPullToRefresh()
    }

    private fun onPullToRefresh() {
        pullToRefresh.setOnRefreshListener {
            mDealsPresenter.processGetDeals(
                Constants.SESSION_TOKEN_VALUE,
                Constants.FEATURED_FRAGMENT
            )
            mProgressDialog.show()
            mDealsAdapter!!.notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
        }
    }

    private fun setAdapter(data: List<Datum>) {
        mDealsAdapter = DealsAdapter(data.toMutableList())
        recycler_view?.adapter = mDealsAdapter
    }

    override fun onDealsSuccess(featuredDealsResponse: DealsResponse) {

        mProgressDialog.dismiss()
        if(featuredDealsResponse != null){
            mFeaturedDealsResponse = featuredDealsResponse
            setAdapter(mFeaturedDealsResponse.deals?.data!!)
        }
    }

    override fun setPresenter(presenter: DealsContract.DealsPresenter) {

        mDealsPresenter = presenter as DealsPresenter
    }

    override fun onError() {

    }
}