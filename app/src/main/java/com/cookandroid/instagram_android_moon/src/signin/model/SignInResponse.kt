package com.cookandroid.instagram_android_moon.src.signin.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("result") val result: ResultSignIn
) : BaseResponse()
