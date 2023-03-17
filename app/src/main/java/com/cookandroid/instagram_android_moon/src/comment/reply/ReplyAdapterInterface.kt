package com.cookandroid.instagram_android_moon.src.comment.reply

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse

interface ReplyAdapterInterface {
    fun onGetRepliesSuccess(response: CommentsResponse)

    fun onGetRepliesFailure(message: String)
}