package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeFragmentInterface: HomeFragmentInterface) {

    fun tryGetHomeFeeds() {
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getHomeFeeds().enqueue(object: Callback<HomeFeedsResponse>{
            override fun onResponse(
                call: Call<HomeFeedsResponse>,
                response: Response<HomeFeedsResponse>
            ) {
                homeFragmentInterface.onGetHomeFeedsSuccess(response.body() as HomeFeedsResponse)
            }

            override fun onFailure(call: Call<HomeFeedsResponse>, t: Throwable) {
                homeFragmentInterface.onGetHomeFeedsFailure(t.message ?: "통신 오류")
            }
        })
    }
}