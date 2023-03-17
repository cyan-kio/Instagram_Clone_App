package com.cookandroid.instagram_android_moon.src.main.home.likePosting.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class LikeCommentResponse(
    @SerializedName("result") val result: String
) : BaseResponse()
