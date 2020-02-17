package com.example.dealsassignment.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
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
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.dealsassignment.util.PaginationScrollListener
import java.util.ArrayList

class TopFragment : Fragment(), DealsContract.DealsView {

    private var mDealsAdapter: DealsAdapter? = null
    private lateinit var mDealsPresenter: DealsPresenter
    private lateinit var mTopDealsResponse: DealsResponse
    private lateinit var mTopDealsResponsePerPage: DealsResponse
    private lateinit var mProgressDialog: ProgressDialog
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setTitle("Please wait")

        mDealsPresenter = DealsPresenter(DealsRepository(DealsRemoteDataSource()), this)
        mDealsPresenter.processGetDeals(Constants.SESSION_TOKEN_VALUE, Constants.TOP_FRAGMENT)
        mProgressDialog.show()

        layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view?.setHasFixedSize(true)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view?.layoutManager = layoutManager

        onPullToRefresh()
    }

    private fun onPullToRefresh() {
        pullToRefresh.setOnRefreshListener {
            mDealsPresenter.processGetDeals(
                Constants.SESSION_TOKEN_VALUE,
                Constants.TOP_FRAGMENT
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

    override fun onDealsSuccess(dealsResponse: DealsResponse) {
        mProgressDialog.dismiss()
        if (dealsResponse != null) {
            mTopDealsResponse = dealsResponse
            setAdapter(mTopDealsResponse.deals?.data!!)
        }
    }

    override fun onError() {
        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: DealsContract.DealsPresenter) {

        mDealsPresenter = presenter as DealsPresenter
    }
}