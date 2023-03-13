package com.cookandroid.instagram_android_moon.src.main.home

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeFeedsResponse
import com.cookandroid.instagram_android_moon.src.main.home.model.HomeStoriesResponse
import com.cookandroid.instagram_android_moon.src.main.profile.model.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeFragmentInterface: HomeFragmentInterface) {
    private val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
    fun tryGetHomeFeeds() {
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

    fun tryGetHomeStories() {
        homeRetrofitInterface.getHomeStories().enqueue(object: Callback<HomeStoriesResponse>{
            override fun onResponse(
                call: Call<HomeStoriesResponse>,
                response: Response<HomeStoriesResponse>
            ) {
                homeFragmentInterface.onGetHomeStoriesSuccess(response.body() as HomeStoriesResponse)
            }

            override fun onFailure(call: Call<HomeStoriesResponse>, t: Throwable) {
                homeFragmentInterface.onGetHomeStoriesFailure(t.message ?: "통신 오류")
            }
        })
    }
}