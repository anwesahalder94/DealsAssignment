package com.example.dealsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("fpd_flag")
    @Expose
    var fpdFlag: Boolean? = null
    @SerializedName("off_percent")
    @Expose
    var offPercent: String? = null
    @SerializedName("current_price")
    @Expose
    var currentPrice: Float? = null
    @SerializedName("original_price")
    @Expose
    var originalPrice: Float? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("comments_count")
    @Expose
    var commentsCount: Int? = null
    @SerializedName("all_posts_count")
    @Expose
    var allPostsCount: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: Long? = null
    @SerializedName("score")
    @Expose
    var score: Int? = null
    @SerializedName("vote_value")
    @Expose
    var voteValue: Int? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("description")
    @Expose
    var description: Any? = null
    @SerializedName("share_url")
    @Expose
    var shareUrl: String? = null
    @SerializedName("deal_url")
    @Expose
    var dealUrl: String? = null
    @SerializedName("view_count")
    @Expose
    var viewCount: Int? = null
    @SerializedName("vote_down_reason")
    @Expose
    var voteDownReason: VoteDownReason? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("fpd_suggestted")
    @Expose
    var fpdSuggested: Boolean? = null
    @SerializedName("front_page_suggestions_count")
    @Expose
    var frontPageSuggestionsCount: Int? = null
    @SerializedName("merchant")
    @Expose
    var merchant: Merchant? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
}