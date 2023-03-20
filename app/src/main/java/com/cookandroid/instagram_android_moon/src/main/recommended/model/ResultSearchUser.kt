package com.cookandroid.instagram_android_moon.src.main.recommended.model

import com.google.gson.annotations.SerializedName

data class ResultSearchUser(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("connected_count") val connected_count: Int,
    @SerializedName("connected_friend_nickname") val connected_friend_nickname: String,
    @SerializedName("story_status") val story_status: Int

)
