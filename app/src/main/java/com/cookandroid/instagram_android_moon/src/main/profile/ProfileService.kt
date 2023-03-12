package com.cookandroid.instagram_android_moon.src.main.profile

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val profileFragmentInterface: ProfileFragmentInterface) {

    fun tryGetProfile(user_id: Int){
        val profileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.getProfile(user_id).enqueue(object:Callback<ProfileResponse>
        {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                profileFragmentInterface.onGetProfileSuccess(response.body() as ProfileResponse)
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                profileFragmentInterface.onGetProfileFailure(t.message ?: "통신 오류")
            }
        })
    }

}