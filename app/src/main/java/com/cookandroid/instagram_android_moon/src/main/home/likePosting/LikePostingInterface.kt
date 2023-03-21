package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse

interface LikePostingInterface {

    fun onPostLikePostingSuccess(response: LikePostingResponse)

    fun onPostLikePostingFailure(message: String)

    fun onPatchEditLikePostingSuccess(response: LikePostingResponse)

    fun onPatchEditLikePostingFailure(message: String)
}