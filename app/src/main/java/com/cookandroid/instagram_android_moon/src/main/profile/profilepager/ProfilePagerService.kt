package com.cookandroid.instagram_android_moon.src.main.profile.profilepager

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePagerService(val profilePagerFragmentInterface: ProfilePagerFragmentInterface) {

    fun tryGetProfileFeeds(user_id: Int) {
        val profilePagerRetrofitInterface = ApplicationClass.sRetrofit.create(ProfilePagerRetrofitInterface::class.java)
        profilePagerRetrofitInterface.getProfileFeeds(user_id).enqueue(object:Callback<ProfileFeedsResponse>
        {
            override fun onResponse(
                call: Call<ProfileFeedsResponse>,
                response: Response<ProfileFeedsResponse>
            ) {
                profilePagerFragmentInterface.onGetProfileFeedsSuccess(response.body() as ProfileFeedsResponse)
            }

            override fun onFailure(call: Call<ProfileFeedsResponse>, t: Throwable) {
                profilePagerFragmentInterface.onGetProfileFeedsFailure(t.message ?: "통신 오류")
            }
        })
    }
}