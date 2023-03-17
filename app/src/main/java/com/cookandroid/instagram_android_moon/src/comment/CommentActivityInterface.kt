package com.cookandroid.instagram_android_moon.src.comment

import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.WriteCommentResponse

interface CommentActivityInterface {
    fun onGetCommentsSuccess(response: CommentsResponse)

    fun onGetCommentsFailure(message: String)

    fun onPostWriteCommentSuccess(response: WriteCommentResponse)

    fun onPostWriteCommentFailure(message: String)
}