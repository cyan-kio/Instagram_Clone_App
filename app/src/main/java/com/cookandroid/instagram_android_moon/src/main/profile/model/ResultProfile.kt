package com.cookandroid.instagram_android_moon.src.main.profile.model

import com.google.gson.annotations.SerializedName

data class ResultProfile(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("introduce") val introduce: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("phone_number") val phone_number: String,
    @SerializedName("email_address") val email_address: String,
    @SerializedName("birth_date") val birth_date: String,
    @SerializedName("profile_image_url") val profile_image_url: String,
    @SerializedName("follower_count") val follower_count: Int,
    @SerializedName("following_count") val following_count: Int,
    @SerializedName("post_count") val post_count: Int,
    @SerializedName("connected_count") val connected_count: Int,
    @SerializedName("connected_friend_profiles") val connected_friend_profiles: Array<String>,
    @SerializedName("account_status") val account_status: Boolean,
    @SerializedName("created_at") val created_at: Int,
    @SerializedName("updated_at") val updated_at: String

)
