package com.cookandroid.instagram_android_moon.src.main.follows.model

import com.google.gson.annotations.SerializedName

data class ResultFollowing (
    @SerializedName("follower_count") val follower_count: Int,
    @SerializedName("following_count") val following_count: Int,
    @SerializedName("connected_count") val connected_count: Int,
    @SerializedName("followings") val followings: MutableList<Follows>
)