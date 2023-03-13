package com.cookandroid.instagram_android_moon.src.main.home.model

import com.google.gson.annotations.SerializedName

data class ResultHomeStories (
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("self_status") val self_status: Int,
    @SerializedName("view_status") val view_status: Int,
    @SerializedName("updated_at") val updated_at: String
)