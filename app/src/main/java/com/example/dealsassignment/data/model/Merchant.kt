package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Merchant {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null
    @SerializedName("recommendation")
    @Expose
    var recommendation: Int? = null
    @SerializedName("recommendation_flag")
    @Expose
    var recommendationFlag: Boolean? = null
    @SerializedName("average_rating")
    @Expose
    var averageRating: String? = null
}