package com.cookandroid.instagram_android_moon.src.main.follows.model

import com.google.gson.annotations.SerializedName

data class Follows(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("follow_status") val follow_status: Int,
    @SerializedName("story_status") val story_status: Int
)
