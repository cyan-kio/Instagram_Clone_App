package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse

interface RecommendedFragmentInterface {

    fun onGetRecommendedSuccess(response: RecommendedResponse)

    fun onGetRecommendedFailure(message: String)
}