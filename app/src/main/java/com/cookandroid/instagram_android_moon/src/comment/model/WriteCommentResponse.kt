package com.cookandroid.instagram_android_moon.src.comment.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class WriteCommentResponse(
    @SerializedName("result") val result: ResultWriteComment
) : BaseResponse()
