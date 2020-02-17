package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VoteDownReason {
    @SerializedName("Referral link")
    @Expose
    var referralLink: Int? = null
    @SerializedName("Better price elsewhere")
    @Expose
    var betterPriceElsewhere: Int? = null
    @SerializedName("Product not good/not worth the price")
    @Expose
    var productNotGoodNotWorthThePrice: Int? = null
    @SerializedName("Other reasons")
    @Expose
    var otherReasons: Int? = null
    @SerializedName("Invalid/User Specific Coupon/Deal")
    @Expose
    var invalidUserSpecificCouponDeal: Int? = null
    @SerializedName("Repost")
    @Expose
    var repost: Int? = null
    @SerializedName("Self Promotion")
    @Expose
    var selfPromotion: Int? = null
}