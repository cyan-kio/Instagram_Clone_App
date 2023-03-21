package com.cookandroid.instagram_android_moon.src.main.home.scrapPosting.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ScrapPostingResponse(
    @SerializedName("result") val result: String
) : BaseResponse()
