package com.cookandroid.instagram_android_moon.src.signin.model

import com.google.gson.annotations.SerializedName

data class ResultSignIn(
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("jwt") val jwt: String
)
