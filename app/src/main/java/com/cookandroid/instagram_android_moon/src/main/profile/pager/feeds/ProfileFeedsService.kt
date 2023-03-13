package com.cookandroid.instagram_android_moon.src.main.profile.pager.feeds

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileFeedsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFeedsService(val profileFeedsFragmentInterface: ProfileFeedsFragmentInterface) {

    fun tryGetProfileFeeds(user_id: Int) {
        val profileFeedsRetrofitInterface = ApplicationClass.sRetrofit.create(
            ProfileFeedsRetrofitInterface::class.java)
        profileFeedsRetrofitInterface.getProfileFeeds(user_id).enqueue(object:Callback<ProfileFeedsResponse>
        {
            override fun onResponse(
                call: Call<ProfileFeedsResponse>,
                response: Response<ProfileFeedsResponse>
            ) {
                profileFeedsFragmentInterface.onGetProfileFeedsSuccess(response.body() as ProfileFeedsResponse)
            }

            override fun onFailure(call: Call<ProfileFeedsResponse>, t: Throwable) {
                profileFeedsFragmentInterface.onGetProfileFeedsFailure(t.message ?: "통신 오류")
            }
        })
    }
}