package com.cookandroid.instagram_android_moon.src.signup.model

import com.google.gson.annotations.SerializedName

data class PostPhoneSignUpRequest(
    @SerializedName("phone_number") val phone_number: String,
    @SerializedName("birth_date") val birth_date: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("password") val password: String,
    @SerializedName("profile_image_url") val profile_image_url: String
)
