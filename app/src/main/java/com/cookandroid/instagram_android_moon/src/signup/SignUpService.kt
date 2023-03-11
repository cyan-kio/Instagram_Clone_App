package com.cookandroid.instagram_android_moon.src.signup

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.signup.model.PostEmailSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.PostPhoneSignUpRequest
import com.cookandroid.instagram_android_moon.src.signup.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val signUpActivityInterface: SignUpActivityInterface) {

    fun tryPostPhoneSignUp(postPhoneSignUpRequest: PostPhoneSignUpRequest) {
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
        signUpRetrofitInterface.postPhoneSignUp(postPhoneSignUpRequest).enqueue(object:Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                signUpActivityInterface.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                signUpActivityInterface.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostEmailSignUp(postEmailSignUpRequest: PostEmailSignUpRequest) {
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
        signUpRetrofitInterface.postEmailSignUp(postEmailSignUpRequest).enqueue(object:Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                signUpActivityInterface.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                signUpActivityInterface.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }
}