package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.main.recommended.model.SearchUserResponse

interface RecommendedFragmentInterface {

    fun onGetRecommendedSuccess(response: RecommendedResponse)

    fun onGetRecommendedFailure(message: String)

    fun onGetSearchUserSuccess(response: SearchUserResponse)

    fun onGetSearchUserFailure(message: String)
}