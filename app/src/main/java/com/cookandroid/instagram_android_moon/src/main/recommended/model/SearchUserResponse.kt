package com.cookandroid.instagram_android_moon.src.main.recommended.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("result") val result: MutableList<ResultSearchUser>
) : BaseResponse()
