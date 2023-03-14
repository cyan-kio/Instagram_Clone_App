package com.cookandroid.instagram_android_moon.src.main.follows.pager.followings

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowingsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingsService(val followingsFragmentInterface: FollowingsFragmentInterface) {

    fun tryGetFollowsFollowings(user_id: Int) {
        val followingsRetrofitInterface = ApplicationClass.sRetrofit.create(
            FollowingsRetrofitInterface::class.java)
        followingsRetrofitInterface.getFollowsFollowings(user_id).enqueue(object: Callback<FollowingsResponse>{

            override fun onResponse(
                call: Call<FollowingsResponse>,
                response: Response<FollowingsResponse>
            ) {
                followingsFragmentInterface.onGetFollowsFollowingsSuccess(response.body() as FollowingsResponse)
            }

            override fun onFailure(call: Call<FollowingsResponse>, t: Throwable) {
                followingsFragmentInterface.onGetFollowsFollowingsFailure(t.message ?: "통신 오류")
            }
        })
    }
}