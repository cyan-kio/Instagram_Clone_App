package com.cookandroid.instagram_android_moon.src.signup

import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse

interface SignUpActivityInterface {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}