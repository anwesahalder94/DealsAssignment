package com.example.dealsassignment.data.remote.api

import com.example.dealsassignment.data.model.DealsResponse
import com.example.dealsassignment.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface DealsAPI {
    @GET(Constants.TOP_DEALS)
    fun getTopDealsResponse(@Header(Constants.SESSION_TOKEN_HEADER) sessionToken: String): Call<DealsResponse>

    @GET(Constants.POPULAR_DEALS)
    fun getPopularDealsResponse(@Header(Constants.SESSION_TOKEN_HEADER) sessionToken: String): Call<DealsResponse>

    @GET(Constants.FEATURED_DEALS)
    fun getFeaturedDealsResponse(@Header(Constants.SESSION_TOKEN_HEADER) sessionToken: String): Call<DealsResponse>

    @GET(Constants.TOP_DEALS)
    fun getTopDealsResponsePerPage(@Header(Constants.SESSION_TOKEN_HEADER) sessionToken: String, @Query(Constants.PER_PAGE) perPage: Int): Call<DealsResponse>

}