package com.cookandroid.instagram_android_moon.src.main.recommended.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RecommendedResponse(
    @SerializedName("result") val result: MutableList<ResultRecommended>
) : BaseResponse()
