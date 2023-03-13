package com.cookandroid.instagram_android_moon.src.comment.model

import com.cookandroid.instagram_android_moon.config.BaseResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.ResultHomeFeeds
import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("result") val result: MutableList<ResultComments>
): BaseResponse()
