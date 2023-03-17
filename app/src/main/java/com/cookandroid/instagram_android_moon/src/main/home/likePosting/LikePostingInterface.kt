package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse

interface LikePostingInterface {

    fun onPostLikePostingSuccess(response: LikeCommentResponse)

    fun onPostLikePostingFailure(message: String)

    fun onPatchUnLikePostingSuccess(response: LikeCommentResponse)

    fun onPatchUnLikePostingFailure(message: String)
}