package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse

interface CommentActivityInterface {
    fun onGetCommentsSuccess(response: CommentsResponse)

    fun onGetCommentsFailure(message: String)
}