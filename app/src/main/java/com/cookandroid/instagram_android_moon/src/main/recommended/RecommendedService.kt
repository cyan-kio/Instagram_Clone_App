package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendedService(val recommendedFragmentInterface: RecommendedFragmentInterface) {

    fun tryGetRecommended(){
        val recommendedRetrofitInterface = ApplicationClass.sRetrofit.create(RecommendedRetrofitInterface::class.java)
        recommendedRetrofitInterface.getRecommended().enqueue(object:Callback<RecommendedResponse>{
            override fun onResponse(
                call: Call<RecommendedResponse>,
                response: Response<RecommendedResponse>
            ) {
                recommendedFragmentInterface.onGetRecommendedSuccess(response.body() as RecommendedResponse)
            }

            override fun onFailure(call: Call<RecommendedResponse>, t: Throwable) {
                recommendedFragmentInterface.onGetRecommendedFailure(t.message ?: "통신 오류")
            }
        })
    }

}