package com.cookandroid.instagram_android_moon.src.main.recommended.view.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ViewResponse(
    @SerializedName("result") val result: ResultView
) : BaseResponse()
