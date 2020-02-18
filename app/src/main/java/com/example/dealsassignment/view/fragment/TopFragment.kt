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
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.dealsassignment.util.AppUtils

class TopFragment : Fragment(), DealsContract.DealsView {

    private var mDealsAdapter: DealsAdapter? = null
    private lateinit var mDealsPresenter: DealsPresenter
    private lateinit var mTopDealsResponse: DealsResponse
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
        mProgressDialog.setCancelable(false)

        //initializing the presenter
        mDealsPresenter = DealsPresenter(DealsRepository(DealsRemoteDataSource()), this)

        //calling the function present in presenter by checking if the network is available or not
        if (AppUtils.isNetworkAvailable(activity!!.applicationContext)) {
            mProgressDialog.show()
            mDealsPresenter.processGetDeals(Constants.SESSION_TOKEN_VALUE, Constants.TOP_FRAGMENT)
        }else{
            Toast.makeText(activity!!.applicationContext, "Please check your internet", Toast.LENGTH_LONG).show()
        }

        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view?.setHasFixedSize(true)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view?.layoutManager = layoutManager

        onPullToRefresh()
    }

    // onPullToRefresh is used to pull it down (and it will bounce back when released) to refresh the content in the fragment.
    private fun onPullToRefresh() {
        pullToRefresh.setOnRefreshListener {
            if (AppUtils.isNetworkAvailable(activity!!.applicationContext)) {
                mProgressDialog.show()
                mDealsPresenter.processGetDeals(
                    Constants.SESSION_TOKEN_VALUE,
                    Constants.TOP_FRAGMENT
                )
            }else{
                Toast.makeText(activity!!.applicationContext, "Please check your internet", Toast.LENGTH_LONG).show()
            }
            mDealsAdapter!!.notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
        }
    }

    //setting up adapter
    private fun setAdapter(data: List<Datum>) {
        mDealsAdapter = DealsAdapter(data.toMutableList(), activity!!.applicationContext)
        recycler_view?.adapter = mDealsAdapter
    }

    /**
     * onDealsSuccess method is used to show the success deals response
     */
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

    /**
     * setPresenter method is used to set the presenter from the view
     */
    override fun setPresenter(presenter: DealsContract.DealsPresenter) {

        mDealsPresenter = presenter as DealsPresenter
    }
}