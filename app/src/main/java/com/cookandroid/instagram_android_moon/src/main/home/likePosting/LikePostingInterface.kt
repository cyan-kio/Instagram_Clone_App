package com.cookandroid.instagram_android_moon.src.main.home.likePosting

import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikePostingResponse

interface LikePostingInterface {

    fun onPostLikePostingSuccess(response: LikePostingResponse)

    fun onPostLikePostingFailure(message: String)

    fun onPatchUnLikePostingSuccess(response: LikePostingResponse)

    fun onPatchUnLikePostingFailure(message: String)
}