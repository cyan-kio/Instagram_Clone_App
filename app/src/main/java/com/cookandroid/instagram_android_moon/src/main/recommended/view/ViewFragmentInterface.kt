package com.cookandroid.instagram_android_moon.src.main.recommended.view

import com.cookandroid.instagram_android_moon.src.main.recommended.view.model.ViewResponse

interface ViewFragmentInterface {

    fun onGetViewSuccess(response: ViewResponse)

    fun onGetViewFailure(message: String)
}