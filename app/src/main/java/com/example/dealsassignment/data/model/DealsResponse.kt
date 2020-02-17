package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DealsResponse {
    @SerializedName("seo_setting")
    @Expose
    var seoSetting: SeoSetting? = null
    @SerializedName("deals")
    @Expose
    var deals: Deals? = null

}