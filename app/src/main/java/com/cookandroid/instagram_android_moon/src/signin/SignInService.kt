package com.cookandroid.instagram_android_moon.src.signin

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.signin.model.PostSignInRequest
import com.cookandroid.instagram_android_moon.src.signin.model.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService(val signInActivityInterface: SignInActivityInterface) {

    fun tryPostSignIn(postSignInRequest: PostSignInRequest){
        val signInRetrofitInterface = ApplicationClass.sRetrofit.create(SignInRetrofitInterface::class.java)
        signInRetrofitInterface.postSignIn(postSignInRequest).enqueue(object:
            Callback<SignInResponse>{
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                signInActivityInterface.onPostSignInSuccess(response.body() as SignInResponse)
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                signInActivityInterface.onPostSignInFailure(t.message ?: "통신 오류")
            }
        })
    }
}