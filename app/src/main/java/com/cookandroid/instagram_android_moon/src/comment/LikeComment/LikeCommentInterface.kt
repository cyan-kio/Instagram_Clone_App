package com.cookandroid.instagram_android_moon.src.comment.LikeComment

import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse

interface LikeCommentInterface {

    fun onPostLikeCommentSuccess(response: LikeCommentResponse)

    fun onPostLikeCommentFailure(message: String)

    fun onPatchEditLikeCommentSuccess(response: LikeCommentResponse)

    fun onPatchEditLikeCommentFailure(message: String)
}