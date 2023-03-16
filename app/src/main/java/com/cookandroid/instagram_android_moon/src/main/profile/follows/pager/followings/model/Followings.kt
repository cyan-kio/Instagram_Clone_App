package com.cookandroid.instagram_android_moon.src.main.profile.follows.pager.followings.model

import com.google.gson.annotations.SerializedName

data class Followings(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("follow_status") val follow_status: Int,
    @SerializedName("story_status") val story_status: Int
)
