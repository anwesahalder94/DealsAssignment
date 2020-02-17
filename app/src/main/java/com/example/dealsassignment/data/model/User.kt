package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("rank")
    @Expose
    var rank: String? = null
    @SerializedName("current_dimes")
    @Expose
    var currentDimes: Int? = null
    @SerializedName("karma")
    @Expose
    var karma: Int? = null
    @SerializedName("fpd_count")
    @Expose
    var fpdCount: Int? = null
}