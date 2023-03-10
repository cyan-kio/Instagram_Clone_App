package com.cookandroid.instagram_android_moon.src.signin

import com.cookandroid.instagram_android_moon.src.signin.model.PostSignInRequest
import com.cookandroid.instagram_android_moon.src.signin.model.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInRetrofitInterface {
    @POST("app/users/login")
    fun postSignIn(@Body params: PostSignInRequest): Call<SignInResponse>
}