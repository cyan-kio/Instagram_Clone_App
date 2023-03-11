package com.cookandroid.instagram_android_moon.src.signup.model

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("user_id") val user_id: Int
)
