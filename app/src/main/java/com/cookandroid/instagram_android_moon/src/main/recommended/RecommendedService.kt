package com.cookandroid.instagram_android_moon.src.main.recommended

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.recommended.model.RecommendedResponse
import com.cookandroid.instagram_android_moon.src.main.recommended.model.SearchUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendedService(val recommendedFragmentInterface: RecommendedFragmentInterface) {
    private val recommendedRetrofitInterface = ApplicationClass.sRetrofit.create(RecommendedRetrofitInterface::class.java)
    fun tryGetRecommended(){
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

    fun tryGetSearchUser(user_keyword: String){
        recommendedRetrofitInterface.getUserResearch(user_keyword).enqueue(object:Callback<SearchUserResponse>{
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                recommendedFragmentInterface.onGetSearchUserSuccess(response.body() as SearchUserResponse)
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                recommendedFragmentInterface.onGetSearchUserFailure(t.message ?: "통신 오류")
            }
        })
    }
}