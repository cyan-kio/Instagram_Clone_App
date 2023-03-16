package com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.profile.follows.followfunction.model.FollowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowFunctionService(val followFunctionInterface: FollowFunctionInterface) {
    private val followFunctionRetrofitInterface = ApplicationClass.sRetrofit.create(
        FollowFunctionRetrofitInterface::class.java)

    fun tryFollow(user_id: Int, follow_user_id: Int) {
        followFunctionRetrofitInterface.follow(user_id, follow_user_id).enqueue(object: Callback<FollowResponse>{
            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                followFunctionInterface.onPostFollowSuccess(response.body() as FollowResponse)
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                followFunctionInterface.onPostFollowFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryUnFollow(user_id: Int, follow_user_id: Int) {
        followFunctionRetrofitInterface.unFollow(user_id, follow_user_id).enqueue(object: Callback<FollowResponse>{
            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                followFunctionInterface.onPostUnFollowSuccess(response.body() as FollowResponse)
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                followFunctionInterface.onPostUnFollowFailure(t.message ?: "통신 오류")
            }
        })
    }
}