package com.cookandroid.instagram_android_moon.src.signin

import com.cookandroid.instagram_android_moon.src.signin.model.SignInResponse

interface SignInActivityInterface {

    fun onPostSignInSuccess(response: SignInResponse)

    fun onPostSignInFailure(message: String)
}