package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Deals {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
}