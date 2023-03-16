package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followers.model

import com.google.gson.annotations.SerializedName

data class ResultFollowers (
    @SerializedName("follower_count") val follower_count: Int,
    @SerializedName("following_count") val following_count: Int,
    @SerializedName("connected_count") val connected_count: Int,
    @SerializedName("followers") val followers: MutableList<Followers>
)