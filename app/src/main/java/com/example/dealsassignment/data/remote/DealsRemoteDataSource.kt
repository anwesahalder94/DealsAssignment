package com.example.dealsassignment.data.remote

import android.util.Log
import com.example.dealsassignment.data.DataSourceCallBack
import com.example.dealsassignment.data.DealsDataSource
import com.example.dealsassignment.data.RetrofitException
import com.example.dealsassignment.data.model.DealsResponse
import com.example.dealsassignment.data.remote.api.DealsAPI
import com.example.dealsassignment.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class DealsRemoteDataSource : DealsDataSource {

    var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    var dealsAPI: DealsAPI = retrofit.create(DealsAPI::class.java)

    override fun performGetTopDeals(
        sessionToken: String,
        fragmentName: String,
        getDealsCallBack: DataSourceCallBack<DealsResponse>
    ) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        when (fragmentName) {
            Constants.TOP_FRAGMENT -> dealsAPI.getTopDealsResponse(sessionToken)
                .enqueue(object : Callback<DealsResponse> {

                    override fun onResponse(
                        call: Call<DealsResponse>?,
                        response: Response<DealsResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            val gson = Gson()
                            val json = gson.toJson(response.body())
                            val topDealsResponse: DealsResponse =
                                gson.fromJson(json, DealsResponse::class.java)
                            getDealsCallBack.onSuccess(topDealsResponse)

                        } else if (response.code() == 401) {
                            getDealsCallBack.onError()
                        }
                    }

                    override fun onFailure(call: Call<DealsResponse>?, t: Throwable?) {
                        Log.d("Error", t.toString())

                        getDealsCallBack.onError()

                    }
                })

            Constants.POPULAR_FRAGMENT -> dealsAPI.getPopularDealsResponse(sessionToken)
                .enqueue(object : Callback<DealsResponse> {

                    override fun onResponse(
                        call: Call<DealsResponse>?,
                        response: Response<DealsResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            val gson = Gson()
                            val json = gson.toJson(response.body())
                            val popularDealsResponse: DealsResponse =
                                gson.fromJson(json, DealsResponse::class.java)
                            getDealsCallBack.onSuccess(popularDealsResponse)
                        }
                    }

                    override fun onFailure(call: Call<DealsResponse>?, t: Throwable?) {
                        Log.d("Error", t.toString())
                    }
                })

            Constants.FEATURED_FRAGMENT -> dealsAPI.getFeaturedDealsResponse(sessionToken)
                .enqueue(object : Callback<DealsResponse> {

                    override fun onResponse(
                        call: Call<DealsResponse>?,
                        response: Response<DealsResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            val gson = Gson()
                            val json = gson.toJson(response.body())
                            val featuredDealsResponse: DealsResponse =
                                gson.fromJson(json, DealsResponse::class.java)
                            getDealsCallBack.onSuccess(featuredDealsResponse)
                        }
                    }

                    override fun onFailure(call: Call<DealsResponse>?, t: Throwable?) {
                        Log.d("Error", t.toString())
                    }
                })
        }
    }
}