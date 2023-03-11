package com.cookandroid.instagram_android_moon.src.signup

import com.cookandroid.instagram_android_moon.src.signup.model.PostEmailSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.PostPhoneSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("app/users/phone")
    fun postPhoneSignUp(@Body params: PostPhoneSignUpRequest): Call<SignUpResponse>

    @POST("app/users/email")
    fun postEmailSignUp(@Body params: PostEmailSignUpRequest): Call<SignUpResponse>
}