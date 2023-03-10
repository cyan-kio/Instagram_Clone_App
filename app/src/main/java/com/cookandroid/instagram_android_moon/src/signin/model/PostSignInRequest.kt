package com.cookandroid.instagram_android_moon.src.signin.model

import com.google.gson.annotations.SerializedName

data class PostSignInRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String,
)
