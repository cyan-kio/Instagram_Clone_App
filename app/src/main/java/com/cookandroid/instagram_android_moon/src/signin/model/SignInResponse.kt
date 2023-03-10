package com.cookandroid.instagram_android_moon.src.signin.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<ResultSignIn>
)
