package com.cookandroid.instagram_android_moon.src.main.follows.pager.followers

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.follows.model.FollowersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersService(val followersFragmentInterface: FollowersFragmentInterface) {
    private val followersRetrofitInterface = ApplicationClass.sRetrofit.create(
        FollowersRetrofitInterface::class.java)
    fun tryGetFollowsFollowers(user_id: Int) {
        followersRetrofitInterface.getFollowsFollowers(user_id).enqueue(object: Callback<FollowersResponse>{
            override fun onResponse(
                call: Call<FollowersResponse>,
                response: Response<FollowersResponse>
            ) {
                followersFragmentInterface.onGetFollowsFollowersSuccess(response.body() as FollowersResponse)
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                followersFragmentInterface.onGetFollowsFollowersFailure(t.message ?: "통신 오류")
            }
        })
    }
}