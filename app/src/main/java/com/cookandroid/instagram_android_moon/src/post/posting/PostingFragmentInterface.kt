package com.cookandroid.instagram_android_moon.src.post.posting

import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.post.model.PostResponse

interface PostingFragmentInterface {

    fun onPostPostSuccess(response: PostResponse)

    fun onPostPostFailure(message: String)
}