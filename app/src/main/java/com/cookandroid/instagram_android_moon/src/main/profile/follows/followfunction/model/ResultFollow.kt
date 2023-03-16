package com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model

import com.google.gson.annotations.SerializedName

data class ResultFollow (
    @SerializedName("status") val status: Int,
    @SerializedName("user_follow_id") val user_follow_id: Int
)